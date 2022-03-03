package com.semicolon.walkhub.viewmodel.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.entity.exercise.ExerciseAnalysisResultEntity
import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.domain.exception.basic.NoInternetException
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.usecase.exercise.FetchDailyExerciseRecordUseCase
import com.semicolon.domain.usecase.exercise.FetchExerciseAnalysisResultUseCase
import com.semicolon.domain.usecase.level.FetchLevelListUseCase
import com.semicolon.domain.usecase.user.PostUserSignInUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import com.semicolon.walkhub.viewmodel.hub.HubSearchSchoolViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ActivityAnalysisViewModel @Inject constructor(
    private val fetchExerciseAnalysisResultUseCase: FetchExerciseAnalysisResultUseCase,
    private val fetchDailyExerciseRecordUseCase: FetchDailyExerciseRecordUseCase,
    private val fetchLevelListUseCase: FetchLevelListUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchExerciseAnalysisResult() = viewModelScope.launch {
        kotlin.runCatching {
            fetchExerciseAnalysisResultUseCase.execute(Unit).collect {
                emitEvent(Event.AnalysisResult(it))
            }
        }.onFailure {
            when (it) {
                is NoInternetException -> emitEvent(Event.MessageEvent("인터넷을 사용할 수 없습니다"))
            }
        }
    }


    fun fetchDailyExerciseRecordUseCase() = viewModelScope.launch {
        kotlin.runCatching {
            fetchDailyExerciseRecordUseCase.execute(Unit).collect {
                emitEvent(Event.ExerciseRecord(it))
            }
        }
    }

    fun fetchLevelList() = viewModelScope.launch {
        kotlin.runCatching {
            fetchLevelListUseCase.execute(Unit).collect {
                emitEvent(Event.Level(it))
            }
        }.onFailure {
            when (it) {
                is NoInternetException -> emitEvent(Event.MessageEvent("인터넷을 사용할 수 없습니다"))
            }
        }
    }

    private suspend fun emitEvent(event: Event) {
        _eventFlow.emit(event)
    }

    sealed class Event {
        data class AnalysisResult(val result: ExerciseAnalysisResultEntity) : Event()
        data class ExerciseRecord(val record: DailyExerciseEntity) : Event()
        data class Level(val level: List<LevelEntity>) : Event()
        data class MessageEvent(val text: String) : Event()
    }
}