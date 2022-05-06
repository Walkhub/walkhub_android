package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.usecase.exercise.FinishMeasureExerciseUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FinishMeasureViewModel @Inject constructor(
    private val finishMeasureExerciseUseCase: FinishMeasureExerciseUseCase
) : ViewModel() {

    private var imageFile: File? = null

    private val _event = MutableEventFlow<Event>()
    val event = _event.asEventFlow()

    fun setImageFile(file: File) {
        imageFile = file
    }

    fun finishMeasure() {
        viewModelScope.launch {
            if(imageFile != null) {
                kotlin.runCatching {
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