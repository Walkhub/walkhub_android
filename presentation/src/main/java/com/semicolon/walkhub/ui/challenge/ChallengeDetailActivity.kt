package com.semicolon.walkhub.ui.challenge

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityChallengeDetailBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.challenge.ChallengeDetailViewModel
import com.semicolon.walkhub.viewmodel.challenge.model.ChallengeDetailData
import com.semicolon.walkhub.viewmodel.challenge.model.TodayWalkData
import kotlin.math.round

class ChallengeDetailActivity : BaseActivity<ActivityChallengeDetailBinding>(R.layout.activity_challenge_detail) {

    private val vm: ChallengeDetailViewModel by viewModels()

    override fun initView() {
        val challengeId = intent.getLongExtra("challenge_id", -1L)

        vm.fetchChallengeDetail(challengeId.toInt())

        repeatOnStarted {
            vm.eventFlow.collect { handleEvent(it) }
        }
        binding.tvJoin.setOnClickListener {
            vm.participateChallenge(challengeId.toInt())
        }
    }

    fun handleEvent(event: ChallengeDetailViewModel.Event) {
        when (event) {
            is ChallengeDetailViewModel.Event.FetchChallengeDetailSuccess -> {
                setChallengeDetail(
                    challengeDetailData = event.challengeDetailData,
                    currentWalkData = event.dailyExerciseData
                )
            }
            is ChallengeDetailViewModel.Event.ParticipateChallengeSuccess -> {
                binding.tvJoin.visibility = View.GONE
            }
            is ChallengeDetailViewModel.Event.FetchChallengeDetailErrorMessage -> {
                //TODO 데이터를 가져오는데 실패했습니다
            }
            is ChallengeDetailViewModel.Event.ParticipateChallengeErrorMessage -> {
                //TODO 참여에 실패했습니다
            }
        }
    }

    private fun setChallengeDetail(
        challengeDetailData: ChallengeDetailData,
        currentWalkData: TodayWalkData
    ) {
        binding.ivChallenge.insertImage(this, challengeDetailData.imageUrl)
        binding.tvChallengeName.text = challengeDetailData.writerEntity.name
        binding.ciivMade.insertImage(this, challengeDetailData.writerEntity.profileImageUrl)
        binding.tvGoal.text =
            "기간 내 ${walkType(challengeDetailData.goal, challengeDetailData.goalType)}달성"
        binding.tvReword.text = challengeDetailData.award
        binding.tvNowKm.text = currentWalk(
            challengeDetailData.value,
            challengeDetailData.goalType,
            currentWalkData
        )
        binding.tvPercent.text = percent(
            challengeDetailData.value,
            challengeDetailData.goalType,
            currentWalkData,
            challengeDetailData.goal
        )
        binding.tvEnd.text = walkType(challengeDetailData.goal, challengeDetailData.goalType)
        binding.tvDescription.text = challengeDetailData.content
        participantList(challengeDetailData.participantList, challengeDetailData.participantCount)
        participant(challengeDetailData.isMine, challengeDetailData.isParticipated)
    }

    private fun walkType(goal: Int, type: ChallengeGoalType): String {
        return when (type) {
            ChallengeGoalType.WALK -> "${goal}걸음"
            else -> "${goal}km"
        }
    }

    private fun currentWalk(
        current: Int,
        type: ChallengeGoalType,
        todayWalk: TodayWalkData
    ): String {
        return when (type) {
            ChallengeGoalType.WALK -> "${current + todayWalk.stepCount}걸음"
            else -> "${round((current / 100.toDouble() + todayWalk.traveledDistanceAsMeter) * 10) / 10000}km"
        }
    }

    private fun percent(
        current: Int,
        type: ChallengeGoalType,
        todayWalk: TodayWalkData,
        goal: Int
    ): String {
        return when (type) {
            ChallengeGoalType.WALK ->
                if (current + todayWalk.stepCount >= goal) "100% "
                else "${round(current.toDouble() + todayWalk.stepCount / goal.toDouble() * 100) / 10}%"
            else ->
                if((current/10000 + todayWalk.traveledDistanceAsMeter/100) > goal) "100%"
                else "${((current.toDouble()/10000 + todayWalk.traveledDistanceAsMeter/100)/goal.toDouble()*100)/10}%"
        }
    }

    private fun ImageView.insertImage(context: Context, url: String) {
        Glide.with(context)
            .load(url)
            .into(this)
    }

    private fun participant(isMine: Boolean, isParticipant: Boolean) {
        if (isMine && !isParticipant) binding.tvJoin.visibility = View.VISIBLE
        else binding.tvJoin.visibility = View.GONE
    }

    private fun participantList(data: List<ChallengeDetailData.ParticipantList>, count: Int) {
        when (data.size) {
            0 -> {
                binding.ciivPerson1.visibility = View.GONE
                binding.ciivPerson2.visibility = View.GONE
                binding.ciivPerson3.visibility = View.GONE
                binding.tvJoinPerson.visibility = View.GONE
            }
            1 -> {
                binding.ciivPerson1.insertImage(this, data[0].profileImageUrl)
                binding.ciivPerson2.visibility = View.GONE
                binding.ciivPerson3.visibility = View.GONE
                binding.tvJoinPerson.visibility = View.GONE
            }
            2 -> {
                binding.ciivPerson1.insertImage(this, data[0].profileImageUrl)
                binding.ciivPerson2.insertImage(this, data[1].profileImageUrl)
                binding.ciivPerson3.visibility = View.GONE
                binding.tvJoinPerson.visibility = View.GONE
            }
            3 -> {
                binding.ciivPerson1.insertImage(this, data[0].profileImageUrl)
                binding.ciivPerson2.insertImage(this, data[1].profileImageUrl)
                binding.ciivPerson3.insertImage(this, data[2].profileImageUrl)
                binding.tvJoinPerson.visibility = View.GONE
            }
            else -> {
                binding.ciivPerson1.insertImage(this, data[0].profileImageUrl)
                binding.ciivPerson2.insertImage(this, data[1].profileImageUrl)
                binding.ciivPerson3.insertImage(this, data[2].profileImageUrl)
                binding.tvJoinPerson.text = "$count"
            }
        }
    }
}