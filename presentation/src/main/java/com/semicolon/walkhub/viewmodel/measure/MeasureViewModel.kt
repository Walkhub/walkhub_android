package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.usecase.exercise.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeasureViewModel @Inject constructor(
    private val fetchMeasuredExerciseRecordUseCase: FetchMeasuredExerciseRecordUseCase,
    private val fetchMeasuredTimeUseCase: FetchMeasuredTimeUseCase,
    private val fetchCurrentSpeedUseCase: FetchCurrentSpeedUseCase,
    private val pauseMeasureExerciseUseCase: PauseMeasureExerciseUseCase,
    private val resumeMeasureExerciseUseCase: ResumeMeasureExerciseUseCase,
    private val startMeasureExerciseUseCase: StartMeasureExerciseUseCase,
    private val finishMeasureExerciseUseCase: FinishMeasureExerciseUseCase
) : ViewModel() {

    fun fetchMeasuredExercise() {
        viewModelScope.launch {
            fetchMeasuredExerciseRecordUseCase.execute(Unit).collect {

            }
        }
    }

    fun fetchMeasuredTime() {
        viewModelScope.launch {
            fetchMeasuredTimeUseCase.execute(Unit).collect {

            }
        }
    }

    fun fetchCurrentSpeed() {
        viewModelScope.launch {
            fetchCurrentSpeedUseCase.execute(Unit).collect {

            }
        }
    }

    fun pauseMeasureExercise() {
        viewModelScope.launch {
            pauseMeasureExerciseUseCase.execute(Unit)
        }
    }

    fun resumeMeasureExercise() {
        viewModelScope.launch {
            resumeMeasureExerciseUseCase.execute(Unit)
        }
    }

    fun startMeasureExercise() {
        viewModelScope.launch {
            //startMeasureExerciseUseCase.execute()
        }
    }

    fun finishMeasureExercise() {
        viewModelScope.launch {
            //finishMeasureExerciseUseCase.execute()
        }
    }

}