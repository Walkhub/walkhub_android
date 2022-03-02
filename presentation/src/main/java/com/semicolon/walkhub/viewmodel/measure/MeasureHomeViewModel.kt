package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import com.semicolon.domain.usecase.exercise.FetchExerciseRecordListUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeasureHomeViewModel @Inject constructor(
    private val fetchExerciseRecordListUseCase: FetchExerciseRecordListUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private var _isDistance = MutableStateFlow(true)
    val isDistance = _isDistance.asStateFlow()

    fun fetchExerciseRecordList() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchExerciseRecordListUseCase.execute(Unit).collect {
                    sendEvent(Event.ShowRecordList(it))
                }
            }.onFailure {

            }
        }
    }

    fun switchIsDistance() {
        viewModelScope.launch {
            _isDistance.emit(!isDistance.value)
        }
    }

    private suspend fun sendEvent(event: Event) {
        _eventFlow.emit(event)
    }

    sealed class Event {
        data class ShowRecordList(val recordList: List<ExerciseRecordEntity>) : Event()
    }
}