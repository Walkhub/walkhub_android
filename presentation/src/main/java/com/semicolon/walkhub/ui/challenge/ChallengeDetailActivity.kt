package com.semicolon.walkhub.ui.challenge

import androidx.activity.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityChallengeDetailBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.challenge.ChallengeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeDetailActivity :
    BaseActivity<ActivityChallengeDetailBinding>(R.layout.activity_challenge_detail) {

    private val viewModel: ChallengeDetailViewModel by viewModels()

    override fun initView() {
        val challengeId = intent.getLongExtra("challenge_id", -1L)
        viewModel.fetchChallengeDetail(challengeId)
    }
}