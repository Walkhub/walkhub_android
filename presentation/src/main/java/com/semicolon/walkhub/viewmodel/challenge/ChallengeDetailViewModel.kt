package com.semicolon.walkhub.viewmodel.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.usecase.challenge.FetchChallengeDetailUseCase
import com.semicolon.domain.usecase.challenge.PostParticipateChallengeUseCase
import com.semicolon.domain.usecase.exercise.FetchDailyExerciseRecordUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import com.semicolon.walkhub.viewmodel.challenge.model.ChallengeDetailData
import com.semicolon.walkhub.viewmodel.challenge.model.TodayWalkData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeDetailViewModel @Inject constructor(
    private val postParticipateChallengeUseCase: PostParticipateChallengeUseCase,
    private val fetchChallengeDetailUseCase: FetchChallengeDetailUseCase,
    private val fetchDailyExerciseRecordUseCase: FetchDailyExerciseRecordUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun participateChallenge(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                postParticipateChallengeUseCase.execute(id)
            }.onSuccess { Event.ParticipateChallengeSuccess }
                .onFailure {
                    when (it) {
                        is NoInternetException -> Event.ParticipateChallengeErrorMessage("인터넷 연결을 확인해 주세요")
                        else -> Event.ParticipateChallengeErrorMessage("알 수 없는 에러가 발생했습니다")
                    }
                }
        }
    }

    fun fetchChallengeDetail(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchChallengeDetailUseCase.execute(id)
                    .zip(fetchDailyExerciseRecordUseCase.execute(Unit)) { challengeDetailEntity, dailyExerciseEntity ->
                        FetchChallengeDetail(challengeDetailEntity, dailyExerciseEntity)
                    }
                    .collect { Event.FetchChallengeDetailSuccess(it.challengeDetailEntity.toData(), it.dailyExerciseEntity.toData()) }
            }.onFailure {
                when (it) {
                    is NoInternetException -> Event.FetchChallengeDetailErrorMessage("인터넷 연결을 확인해 주세요")
                    else -> Event.FetchChallengeDetailErrorMessage("알 수 없는 에러가 발생했습니다")
                }
            }
        }
    }

    private data class FetchChallengeDetail(
        val challengeDetailEntity: ChallengeDetailEntity,
        val dailyExerciseEntity: DailyExerciseEntity
    )

    sealed class Event {
        object ParticipateChallengeSuccess : Event()
        data class FetchChallengeDetailSuccess(
            val challengeDetailData: ChallengeDetailData,
            val dailyExerciseData: TodayWalkData
        ) : Event()

        data class ParticipateChallengeErrorMessage(val msg: String) : Event()
        data class FetchChallengeDetailErrorMessage(val msg: String) : Event()
    }

    private fun DailyExerciseEntity.toData() =
        TodayWalkData(
            stepCount = stepCount,
            traveledDistanceAsMeter = traveledDistanceAsMeter
        )

    private fun ChallengeDetailEntity.toData() =
        ChallengeDetailData(
            name = name,
            content = content,
            goal = goal,
            goalType = goalType,
            goalScope = goalScope,
            userScope = userScope,
            award = award,
            imageUrl = imageUrl,
            startAt = startAt,
            endAt = endAt,
            isMine = isMine,
            value = value,
            writerEntity = writerEntity.toData(),
            isParticipated = isParticipated,
            participantCount = participantCount,
            participantList = participantList.map { it.toData() }
        )

    private fun ChallengeDetailEntity.WriterEntity.toData() =
        ChallengeDetailData.WriterEntity(
            id = id,
            name = name,
            profileImageUrl = profileImageUrl
        )

    private fun ChallengeDetailEntity.ParticipantList.toData() =
        ChallengeDetailData.ParticipantList(
            id = id,
            name = name,
            profileImageUrl = profileImageUrl
        )
}