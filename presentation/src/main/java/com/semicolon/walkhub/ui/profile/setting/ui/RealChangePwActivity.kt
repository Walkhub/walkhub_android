package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityRealChangePwBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.RealChangePwViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RealChangePwActivity : BaseActivity<ActivityRealChangePwBinding>(
    R.layout.activity_change_pw
) {

    private val vm: RealChangePwViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: password 안 채우기 val password =
        val newPass = binding.nowPw.text.toString()
        //vm.patchUserChangePassword(password = password, newPassword = newPass)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: RealChangePwViewModel.Event) = when (event) {

        is RealChangePwViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
        RealChangePwViewModel.Event.SuccessChange -> {
            val intent = Intent(this, AccountInfoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initView() {
        binding.back.setOnClickListener {
            val intent = Intent(this, AccountInfoActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}