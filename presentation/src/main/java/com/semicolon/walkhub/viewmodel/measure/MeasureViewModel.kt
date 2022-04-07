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
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.io.File
import java.lang.Exception
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
    private val disconnectSocketUseCase: DisconnectSocketUseCase
) : ViewModel() {

    private val _walkCount = MutableLiveData(0)
    val walkCount: LiveData<Int> = _walkCount

    private val _goal = MutableLiveData<GoalEntity>()
    val goal: LiveData<GoalEntity> = _goal

    private val _calorie = MutableLiveData(0F)
    val calorie: LiveData<Float> = _calorie

    private val _speed = MutableLiveData(0F)
    val speed: LiveData<Float> = _speed

    private val _time = MutableLiveData<LocalDateTime>()
    val time: LiveData<LocalDateTime> = _time

    private val _percentage =
        MutableLiveData<Int>() //TODO(걸음수 혹은 거리가 바뀔때 마다 값을 바꿔줘야함 _percentage.value = (값)/(goal) * 100)
    val percentage: LiveData<Int> = _percentage

    private val _measuringState = MutableLiveData(MeasureState.ONGOING)
    val measuringState: LiveData<MeasureState> = _measuringState

    private val _fetchPhoto = MutableEventFlow<Unit>()
    val fetchPhoto = _fetchPhoto.asEventFlow()

    private val _finishMeasuring = MutableEventFlow<Unit>()
    val finishMeasuring = _finishMeasuring.asEventFlow()

    private val _requestPhoto = MutableEventFlow<Unit>()
    val requestPhoto = _requestPhoto.asEventFlow()

    private val _finishActivity = MutableEventFlow<Unit>()
    val finishActivity = _finishActivity.asEventFlow()

    private var _finishPhotoUri: String? = null

    init {
        viewModelScope.launch {
            connectSocketUseCase.execute(Unit)
        }
    }

    fun startMeasureExercise() {
        _measuringState.value = MeasureState.ONGOING
        val goalType = goal.value?.goalType ?: GoalType.WALK_COUNT
        val goal = goal.value?.goal ?: 0
        viewModelScope.launch {
            try {
                startMeasureExerciseUseCase.execute(StartMeasureExerciseParam(goal, goalType))
                fetchMeasuredExercise()
                fetchMeasuredTime()
                fetchCurrentSpeed()
            } catch (e: Exception) {

            }
        }
    }

    private fun fetchMeasuredExercise() {
        viewModelScope.launch {
            fetchMeasuredExerciseRecordUseCase.execute(Unit).collect {
                _walkCount.value = it.stepCount
                _calorie.value = it.burnedKilocalories
            }
        }
    }

    private fun fetchMeasuredTime() {
        viewModelScope.launch {
            fetchMeasuredTimeUseCase.execute(Unit).collect {
                _time.value =
                    LocalDateTime.ofInstant(Instant.ofEpochSecond(it), ZoneId.systemDefault())
            }
        }
    }

    fun fetchMeasuringGoal() {
        viewModelScope.launch {
            val goalResult = fetchGoalUseCase.execute(Unit)
            _goal.value = goalResult
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
            resumeMeasureExerciseUseCase.execute(Unit)
        }
    }

    fun fetchFinishPhoto() {
        viewModelScope.launch {
            _fetchPhoto.emit(Unit)
        }
    }

    fun finishMeasureExercise() {
        viewModelScope.launch {
            if (_finishPhotoUri != null) {
                val imageFile = File(_finishPhotoUri!!)
                val param = FinishMeasureExerciseParam(imageFile)
                finishMeasureExerciseUseCase.execute(param)
                _finishActivity.emit(Unit)
            } else {
                _requestPhoto.emit(Unit)
            }
        }
    }

    fun setImageUri(uri: String) {
        this._finishPhotoUri = uri
        viewModelScope.launch {
            _finishMeasuring.emit(Unit)
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

}