package com.semicolon.walkhub.viewmodel.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
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

                myChallenges.zip(challenges) { my, all ->
                    _challengeItems.value = ArrayList<RecyclerViewItem>().apply {
                        add(
                            RecyclerViewItem(
                                itemLayoutId = R.layout.item_challenge_title,
                                variableId = BR.title,
                                data = "참여 중인 챌린지"
                            )
                        )
                        addAll(
                            my.toMyRecyclerItem()
                        )
                        add(
                            RecyclerViewItem(
                                itemLayoutId = R.layout.item_challenge_title,
                                variableId = BR.title,
                                data = "전체 챌린지"
                            )
                        )
                        addAll(
                            all.toRecyclerItem()
                        )
                    }
                }
            }

        }
    }

    sealed class Event {

        data class ChallengeClick(val id: Long) : Event()
    }

    inner class MyChallengeItemViewModel(
        val id: Long,
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
        val id: Long,
        val title: String,
        val writerName: String,
        val goal: String,
        val periodText: String,
        val award: String,
        val imageUrl: String,
        val participantCount: Int,
        val participantList: List<ChallengeParticipantEntity>
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
        return ChallengeItemViewModel(
            id = id,
            title = name,
            award = award,
            imageUrl = imageUrl,
            participantCount = participantCount,
            participantList = participantList,
            goal = goalText,
            writerName = writer.name,
            periodText = periodText(startAt, endAt)
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