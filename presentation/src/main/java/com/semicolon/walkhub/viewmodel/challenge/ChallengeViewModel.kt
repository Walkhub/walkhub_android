package com.semicolon.walkhub.viewmodel.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.MyChallengeEntity
import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.domain.usecase.challenge.FetchChallengesUseCase
import com.semicolon.domain.usecase.challenge.FetchMyChallengesUseCase
import com.semicolon.walkhub.BR
import com.semicolon.walkhub.R
import com.semicolon.walkhub.adapter.RecyclerViewItem
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val fetchChallengesUseCase: FetchChallengesUseCase,
    private val fetchMyChallengesUseCase: FetchMyChallengesUseCase
) : ViewModel() {

    private val _challengeItems = MutableLiveData<List<RecyclerViewItem>>()
    val challengeItems: LiveData<List<RecyclerViewItem>> = _challengeItems

    private val _event = MutableEventFlow<Event>()
    val event = _event.asEventFlow()

    fun fetchChallenges() {
        viewModelScope.launch {
            kotlin.runCatching {
                val myChallenges = fetchMyChallengesUseCase.execute(Unit)
                val challenges = fetchChallengesUseCase.execute(Unit)

                myChallenges.zip(challenges) { myChallengeList, allChallengeList ->
                    ChallengeItems(
                        myChallengeList,
                        allChallengeList
                    )
                }.collect { challengeList ->
                    _challengeItems.value = ArrayList<RecyclerViewItem>().apply {
                        addTitleItem("참여 중인 챌린지")
                        if (challengeList.myChallenges.isNotEmpty()) {
                            addAll(
                                challengeList.myChallenges.toMyRecyclerItem()
                            )
                        } else {
                            addEmptyCommentItem("참여 중인 챌린지가 없어요.")
                        }

                        addTitleItem("전체 챌린지")
                        if (challengeList.allChallenges.isNotEmpty()) {
                            addAll(
                                challengeList.allChallenges.toRecyclerItem()
                            )
                        } else {
                            addEmptyCommentItem("참여 할 수 있는 챌린지가 없어요.")
                        }
                    }
                }
            }.onFailure {
                _challengeItems.value = ArrayList<RecyclerViewItem>().apply {
                    addTitleItem("참여 중인 챌린지")
                    addEmptyCommentItem("참여 중인 챌린지가 없어요.")
                    addTitleItem("전체 챌린지")
                    addEmptyCommentItem("참여 할 수 있는 챌린지가 없어요.")
                }
            }
        }
    }

    private data class ChallengeItems(
        val myChallenges: List<MyChallengeEntity>,
        val allChallenges: List<ChallengeEntity>
    )

    private fun MutableList<RecyclerViewItem>.addTitleItem(title: String) {
        add(
            RecyclerViewItem(
                itemLayoutId = R.layout.item_challenge_title,
                variableId = BR.title,
                data = title
            )
        )
    }

    private fun MutableList<RecyclerViewItem>.addEmptyCommentItem(comment: String) {
        add(
            RecyclerViewItem(
                itemLayoutId = R.layout.item_challenge_empty,
                variableId = BR.comment,
                data = comment
            )
        )
    }

    sealed class Event {

        data class ChallengeClick(val id: Int) : Event()
    }

    inner class MyChallengeItemViewModel(
        val id: Int,
        val title: String,
        val imageUrl: String,
        val writerName: String,
        val periodText: String,
        val percentage: Int,
        val current: Int,
        val goal: Int
    ) {
        fun onClick() {
            viewModelScope.launch {
                _event.emit(Event.ChallengeClick(id))
            }
        }
    }

    inner class ChallengeItemViewModel(
        val id: Int,
        val title: String,
        val writerName: String,
        val goal: String,
        val periodText: String,
        val award: String,
        val imageUrl: String,
        val participantCount: Int,
        val firstParticipantImage: String,
        val secondParticipantImage: String,
        val thirdParticipantImage: String,
        val participantText: String
    ) {
        fun onClick() {
            viewModelScope.launch {
                _event.emit(Event.ChallengeClick(id))
            }
        }
    }

    private fun ChallengeEntity.toRecyclerItem(): ChallengeItemViewModel {
        val periodText = if (goalScope == ChallengeGoalScope.DAY) "하루 한번" else "기간 내"
        val typeText = if (goalType == ChallengeGoalType.DISTANCE) "KM 달성" else "걸음 달성"
        val goalText = "$periodText  $goal  $typeText"

        val firstParticipantImage = participantList.getOrNull(0)?.profileImageUrl ?: ""
        val secondParticipantImage = participantList.getOrNull(1)?.profileImageUrl ?: ""
        val thirdParticipantImage = participantList.getOrNull(2)?.profileImageUrl ?: ""

        val participantCountText = "$participantCount 명 참여 중입니다."

        val firstParticipantText =
            if (participantList.size > 2) "${participantList[2].name}," else ""
        val secondParticipantText =
            if (participantList.size > 1) "${participantList[1].name}," else ""
        val thirdParticipantText =
            if (participantList.isNotEmpty()) "${participantList[0].name} 외 " else ""
        val participantText =
            firstParticipantText + secondParticipantText + thirdParticipantText + participantCountText

        return ChallengeItemViewModel(
            id = id,
            title = name,
            award = award,
            imageUrl = imageUrl,
            participantCount = participantCount,
            firstParticipantImage = firstParticipantImage,
            secondParticipantImage = secondParticipantImage,
            thirdParticipantImage = thirdParticipantImage,
            goal = goalText,
            writerName = writer.name,
            periodText = periodText(startAt, endAt),
            participantText = participantText
        )
    }

    private fun List<ChallengeEntity>.toRecyclerItem(): List<RecyclerViewItem> =
        map {
            RecyclerViewItem(
                itemLayoutId = R.layout.item_challenge,
                variableId = BR.vm,
                data = it.toRecyclerItem()
            )
        }

    private fun MyChallengeEntity.toRecyclerItem(): MyChallengeItemViewModel {
        val percentage = totalWalkCount / goal * 100

        return MyChallengeItemViewModel(
            id = id,
            title = name,
            imageUrl = imageUrl,
            writerName = writer.name,
            percentage = percentage,
            periodText = periodText(startAt, endAt),
            goal = goal,
            current = totalWalkCount
        )
    }

    private fun periodText(
        startAt: LocalDateTime,
        endAt: LocalDateTime
    ): String =
        "${startAt.year}/" +
                "${String.format("%02d", startAt.monthValue)}/" +
                String.format("%02d", startAt.dayOfMonth) +
                " ~ " +
                "${endAt.year}/" +
                "${String.format("%02d", endAt.monthValue)}/" +
                String.format("%02d", endAt.dayOfMonth)


    private fun List<MyChallengeEntity>.toMyRecyclerItem(): List<RecyclerViewItem> =
        map {
            RecyclerViewItem(
                itemLayoutId = R.layout.item_challenge_my,
                variableId = BR.vm,
                data = it.toRecyclerItem()
            )
        }
}