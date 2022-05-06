package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.usecase.exercise.FinishMeasureExerciseUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FinishMeasureViewModel @Inject constructor(
    private val finishMeasureExerciseUseCase: FinishMeasureExerciseUseCase
) : ViewModel() {

    private var imageRealPath = ""

    private val _event = MutableEventFlow<Event>()
    val event = _event.asEventFlow()

    fun setImageRealPath(path: String) {
        imageRealPath = path
    }

    fun finishMeasure() {
        viewModelScope.launch {
            if(imageRealPath.isNotBlank()) {
                kotlin.runCatching {
                    val imageFile = File(imageRealPath)
                    val parameter = FinishMeasureExerciseParam(imageFile)
                    finishMeasureExerciseUseCase.execute(parameter)
                }.onSuccess {
                    sendEvent(Event.SuccessFinish)
                }.onFailure {
                    sendEvent(Event.FailFinish)
                }
            } else {
                sendEvent(Event.ImagePathIsBlank)
            }
        }
    }

    private fun sendEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    sealed class Event {
        object SuccessFinish : Event()
        object FailFinish : Event()
        object ImagePathIsBlank : Event()
    }
}