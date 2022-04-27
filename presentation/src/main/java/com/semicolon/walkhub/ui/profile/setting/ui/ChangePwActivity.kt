package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityChangePwBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.ChangePwViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePwActivity : BaseActivity<ActivityChangePwBinding>(
    R.layout.activity_change_pw
) {
    private val vm: ChangePwViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val password = binding.nowPw.text.toString()

        binding.goNextBtn.setOnClickListener {
            vm.verifyPassword(password = password)
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: ChangePwViewModel.Event) = when (event) {

        is ChangePwViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
        ChangePwViewModel.Event.SuccessVerify -> {
            val intent = Intent(this, RealChangePwActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
    }
}