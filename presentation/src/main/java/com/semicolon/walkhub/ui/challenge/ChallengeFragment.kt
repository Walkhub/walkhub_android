package com.semicolon.walkhub.ui.challenge

import android.content.Intent
import androidx.fragment.app.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentChallengeBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.viewmodel.challenge.ChallengeViewModel
import kotlinx.coroutines.flow.collect

class ChallengeFragment : BaseFragment<FragmentChallengeBinding>(
    R.layout.fragment_challenge
) {

    private val vm: ChallengeViewModel by viewModels()

    override fun initView() {
        binding.vm = vm
        vm.fetchChallenges()

        repeatOnStarted {
            vm.event.collect { event -> handleEvent(event) }
        }

    }

    private fun handleEvent(event: ChallengeViewModel.Event) {
        when (event) {
            is ChallengeViewModel.Event.ChallengeClick -> {
                startChallengeDetailActivity(event.id)
            }
        }
    }

    private fun startChallengeDetailActivity(id: Long) {
        val intent = Intent(requireContext(), ChallengeDetailActivity::class.java).apply {
            putExtra("challenge_id", id)
        }
        startActivity(intent)
    }
}