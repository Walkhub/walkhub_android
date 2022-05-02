package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.exercise.GoalEntity
import com.semicolon.domain.enums.GoalType
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import com.semicolon.domain.usecase.exercise.*
import com.semicolon.domain.usecase.socket.ConnectSocketUseCase
import com.semicolon.domain.usecase.socket.DisconnectSocketUseCase
import com.semicolon.domain.usecase.socket.ReceiveCheeringUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MeasureViewModel @Inject constructor(
    private val fetchMeasuredExerciseRecordUseCase: FetchMeasuredExerciseRecordUseCase,
    private val fetchMeasuredTimeUseCase: FetchMeasuredTimeUseCase,
    private val fetchCurrentSpeedUseCase: FetchCurrentSpeedUseCase,
    private val fetchGoalUseCase: FetchGoalUseCase,
    private val pauseMeasureExerciseUseCase: PauseMeasureExerciseUseCase,
    private val resumeMeasureExerciseUseCase: ResumeMeasureExerciseUseCase,
    private val startMeasureExerciseUseCase: StartMeasureExerciseUseCase,
    private val finishMeasureExerciseUseCase: FinishMeasureExerciseUseCase,
    private val connectSocketUseCase: ConnectSocketUseCase,
    private val disconnectSocketUseCase: DisconnectSocketUseCase,
    private val receiveCheeringUseCase: ReceiveCheeringUseCase
) : ViewModel() {

    private val _walkCount = MutableLiveData(0)
    val walkCount: LiveData<Int> = _walkCount

    private val _distanceAsKiloMeter = MutableLiveData(0.0)
    val distanceAsKiloMeter: LiveData<Double> = _distanceAsKiloMeter

    private val _goal = MutableLiveData<GoalEntity>()
    val goal: LiveData<GoalEntity> = _goal

    private val _calorie = MutableLiveData(0F)
    val calorie: LiveData<Float> = _calorie

    private val _speed = MutableLiveData(0F)
    val speed: LiveData<Float> = _speed

    private val _time = MutableLiveData<ExercisedTime>()
    val time: LiveData<ExercisedTime> = _time

    private val _percentage = MutableLiveData(0)
    val percentage: LiveData<Int> = _percentage

    private val _measuringState = MutableLiveData(MeasureState.ONGOING)
    val measuringState: LiveData<MeasureState> = _measuringState

    private val _event = MutableEventFlow<Event>()
    val event = _event.asEventFlow()

    private val _cheerUserName = MutableLiveData<String>()
    val cheerUserName: LiveData<String> = _cheerUserName

    private var _finishPhotoUri: String? = null

    init {
        viewModelScope.launch {
            connectSocketUseCase.execute(Unit)
        }
    }

    fun receiveCheering() {
        viewModelScope.launch {
            receiveCheeringUseCase.execute(Unit).runCatching {
                collect {
                    _cheerUserName.value = it
                }
            }
        }
    }

    fun startMeasureExercise() {
        _measuringState.value = MeasureState.ONGOING
        val goalType = goal.value?.goalType ?: GoalType.WALK_COUNT
        val goal = goal.value?.goal ?: 0
        viewModelScope.launch {
            kotlin.runCatching {
                startMeasureExerciseUseCase.execute(StartMeasureExerciseParam(goal, goalType))
                fetchMeasuredData()
            }.onFailure {
                sendEvent(Event.FailStartMeasure)
            }
        }
    }

    private fun fetchMeasuredData() {
        fetchMeasuredExercise()
        fetchMeasuredTime()
        fetchCurrentSpeed()
    }

    private fun fetchMeasuredExercise() {
        viewModelScope.launch {
            fetchMeasuredExerciseRecordUseCase.execute(Unit).collect {
                _walkCount.value = it.stepCount
                _distanceAsKiloMeter.value = (it.traveledDistanceAsMeter / 1000.0)
                _calorie.value = it.burnedKilocalories
                setPercentage()
            }
        }
    }

    private fun setPercentage() {
        val currentValue: Int =
            if (goal.value?.goalType == GoalType.DISTANCE) (distanceAsKiloMeter.value!! * 1000).toInt()
            else walkCount.value ?: 0

        val percentage = (currentValue / (goal.value?.goal ?: 1)) * 100

        _percentage.value = percentage
    }

    private fun fetchMeasuredTime() {
        viewModelScope.launch {
            fetchMeasuredTimeUseCase.execute(Unit).collect {
                val hour = (it / 3600000).toInt()
                val valueForCalculateMinute = if (it >= 3600000) it - 3600000 else it
                val minute = (valueForCalculateMinute / 60000).toInt()
                _time.value = ExercisedTime(hour, minute)
            }
        }
    }

    fun fetchMeasuringGoal() {
        viewModelScope.launch {
            val goalResult = fetchGoalUseCase.execute(Unit)
            _goal.value = goalResult
            fetchMeasuredData()
        }
    }

    fun setDistanceGoal(goal: Int) {
        _goal.value = GoalEntity(goal, GoalType.DISTANCE)
    }

    fun setWalkCountGoal(goal: Int) {
        _goal.value = GoalEntity(goal, GoalType.WALK_COUNT)
    }

    private fun fetchCurrentSpeed() {
        viewModelScope.launch {
            fetchCurrentSpeedUseCase.execute(Unit).collect {
                _speed.value = it
            }
        }
    }

    fun lockMeasureExercise() {
        _measuringState.value = MeasureState.LOCK
    }

    fun unLockMeasureExercise() {
        _measuringState.value = MeasureState.ONGOING
    }

    fun pauseMeasureExercise() {
        _measuringState.value = MeasureState.PAUSED
        viewModelScope.launch {
            pauseMeasureExerciseUseCase.execute(Unit)
        }
    }

    fun resumeMeasureExercise() {
        _measuringState.value = MeasureState.ONGOING
        viewModelScope.launch {
            kotlin.runCatching {
                resumeMeasureExerciseUseCase.execute(Unit)
            }
        }
    }

    fun fetchFinishPhoto() {
        viewModelScope.launch {
            sendEvent(Event.StartFetchPhoto)
        }
    }

    fun finishMeasureExercise() {
        viewModelScope.launch {
            if (_finishPhotoUri != null) {
                val imageFile = File(_finishPhotoUri!!)
                val param = FinishMeasureExerciseParam(imageFile)
                kotlin.runCatching {
                    finishMeasureExerciseUseCase.execute(param)
                }.onSuccess {
                    sendEvent(Event.FinishActivity)
                }.onFailure {
                    sendEvent(Event.FailFinishMeasure)
                }


            } else {
                sendEvent(Event.RequestPhoto)
            }
        }
    }

    fun setImageUri(uri: String) {
        this._finishPhotoUri = uri
        viewModelScope.launch {
            sendEvent(Event.FinishMeasure)
        }
    }

    enum class MeasureState {
        ONGOING,
        PAUSED,
        LOCK
    }

    override fun onCleared() {
        viewModelScope.launch {
            disconnectSocketUseCase.execute(Unit)
        }
        super.onCleared()
    }

    private fun sendEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    data class ExercisedTime(val hour: Int, val minute: Int)

    sealed class Event {
        object StartFetchPhoto : Event()
        object FinishActivity : Event()
        object FinishMeasure : Event()
        object RequestPhoto : Event()
        object FailStartMeasure : Event()
        object FailFinishMeasure : Event()
    }
}