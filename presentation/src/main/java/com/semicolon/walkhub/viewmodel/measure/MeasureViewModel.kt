package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.enums.GoalType
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import com.semicolon.domain.usecase.exercise.*
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MeasureViewModel @Inject constructor(
    private val fetchMeasuredExerciseRecordUseCase: FetchMeasuredExerciseRecordUseCase,
    private val fetchMeasuredTimeUseCase: FetchMeasuredTimeUseCase,
    private val fetchCurrentSpeedUseCase: FetchCurrentSpeedUseCase,
    private val pauseMeasureExerciseUseCase: PauseMeasureExerciseUseCase,
    private val resumeMeasureExerciseUseCase: ResumeMeasureExerciseUseCase,
    private val startMeasureExerciseUseCase: StartMeasureExerciseUseCase,
    private val finishMeasureExerciseUseCase: FinishMeasureExerciseUseCase
) : ViewModel() {

    private val _walkCount = MutableLiveData(0)
    val walkCount: LiveData<Int> = _walkCount

    private val _calorie = MutableLiveData(0F)
    val calorie: LiveData<Float> = _calorie

    private val _speed = MutableLiveData(0F)
    val speed: LiveData<Float> = _speed

    private val _time = MutableLiveData<LocalDateTime>()
    val time: LiveData<LocalDateTime> = _time

    private val _measuringState = MutableLiveData(MeasureState.ONGOING)
    val measuringState: LiveData<MeasureState> = _measuringState

    private val _finishMeasuring = MutableEventFlow<Unit>()
    val finishMeasuring = _finishMeasuring.asEventFlow()


    fun startMeasureExercise(goal: Int, isDistance: Boolean) {
        _measuringState.value = MeasureState.ONGOING
        val goalType = if (isDistance) GoalType.DISTANCE else GoalType.WALK_COUNT
        viewModelScope.launch {
            try {
                startMeasureExerciseUseCase.execute(StartMeasureExerciseParam(goal, goalType))
                fetchMeasuredExercise()
                fetchMeasuredTime()
                fetchCurrentSpeed()
            } catch (e: Exception){

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
                _time.value = LocalDateTime.ofInstant(Instant.ofEpochSecond(it), ZoneId.systemDefault())
            }
        }
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

    fun finishMeasureExercise() {
        viewModelScope.launch {
            _finishMeasuring.emit(Unit)
        }
    }

    enum class MeasureState {
        ONGOING,
        PAUSED,
        LOCK
    }

}