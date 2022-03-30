package com.semicolon.walkhub.ui.challenge

import androidx.fragment.app.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentChallengeBinding
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.viewmodel.challenge.ChallengeViewModel

class ChallengeFragment : BaseFragment<FragmentChallengeBinding>(
    R.layout.fragment_challenge
) {

    private val vm: ChallengeViewModel by viewModels()

    override fun initView() {
        binding.vm = vm
        vm.fetchChallenges()
    }
}