package com.semicolon.walkhub.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.domain.enums.MoreDateType
import com.semicolon.domain.enums.RankScope
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.param.rank.FetchOurSchoolUserRankParam
import com.semicolon.domain.usecase.exercise.FetchDailyExerciseRecordUseCase
import com.semicolon.domain.usecase.exercise.IsMeasuringUseCase
import com.semicolon.domain.usecase.exercise.StartRecordExerciseUseCase
import com.semicolon.domain.usecase.level.FetchLevelListUseCase
import com.semicolon.domain.usecase.level.PatchMaxLevelUseCase
import com.semicolon.domain.usecase.rank.FetchOurSchoolUserRankUseCase
import com.semicolon.walkhub.ui.home.model.HomeData
import com.semicolon.walkhub.ui.home.model.RankData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchLevelListUseCase: FetchLevelListUseCase,
    private val patchMaxLevelUseCase: PatchMaxLevelUseCase,
    private val fetchDailyExerciseRecordUseCase: FetchDailyExerciseRecordUseCase,
    private val startRecordExerciseUseCase: StartRecordExerciseUseCase,
    private val fetchOurSchoolUserRankUseCase: FetchOurSchoolUserRankUseCase,
    private val isMeasuringUseCase: IsMeasuringUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchLevelList() =
        viewModelScope.launch {
            kotlin.runCatching {
                fetchLevelListUseCase.execute(Unit).collect { event(Event.LevelList(it)) }
            }
        }

    fun patchMaxLevel(levelId: Int) =
        viewModelScope.launch { kotlin.runCatching { patchMaxLevelUseCase.execute(levelId) } }

    fun fetchHomeValue() {
        viewModelScope.launch {
            startRecordExerciseUseCase.execute(Unit)
            kotlin.runCatching {
                fetchDailyExerciseRecordUseCase.execute(Unit).collect {
                    event(Event.FetchHomeValue(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    fun fetchSchoolRank() {
        viewModelScope.launch {
            fetchOurSchoolUserRankUseCase.execute(
                FetchOurSchoolUserRankParam(
                    RankScope.SCHOOL,
                    MoreDateType.DAY
                )
            )
            kotlin.runCatching {
                fetchOurSchoolUserRankUseCase.execute(
                    FetchOurSchoolUserRankParam(
                        RankScope.SCHOOL,
                        MoreDateType.DAY
                    )
                ).collect { entity ->
                    event(Event.FetchSchoolRank(entity.rankingList.map { rank -> rank.toData() }))
                }
            }.onFailure {
                it.printStackTrace()
                when (it) {
                    is NullPointerException -> {}
                    is NoInternetException -> event(Event.ErrorMessage("오늘 학교 학생들의 랭킹을 받아오는 것에 실패하였습니다."))
                }
            }
        }
    }

    fun checkMeasuringState() {
        viewModelScope.launch {
            val result = isMeasuringUseCase.execute(Unit)
            if (result == MeasuringState.ONGOING || result == MeasuringState.PAUSED) {
                event(Event.StartMeasure)
            } else {
                event(Event.StartMeasureHome)
            }
        }
    }

    private fun DailyExerciseEntity.toData() =
        HomeData(
            stepCount = stepCount,
            exerciseTimeAsMilli = exerciseTimeAsMilli,
            traveledDistanceAsMeter = traveledDistanceAsMeter,
            burnedKilocalories = burnedKilocalories
        )

    private fun OurSchoolUserRankEntity.Ranking.toData() =
        RankData(
            name = name,
            walkCount = walkCount,
            logoImageUrl = profileImageUrl
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class LevelList(val levelList: List<LevelEntity>) : Event()
        data class FetchSchoolRank(val rankData: List<RankData>) : Event()
        data class FetchHomeValue(val homeData: HomeData) : Event()
        data class ErrorMessage(val message: String) : Event()
        object StartMeasureHome : Event()
        object StartMeasure : Event()
    }
}