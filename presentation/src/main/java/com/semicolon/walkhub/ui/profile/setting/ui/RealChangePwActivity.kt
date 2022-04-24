package com.semicolon.walkhub.ui.profile.setting.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityRealChangePwBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.AccountInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RealChangePwActivity:  BaseActivity<ActivityRealChangePwBinding>(
    R.layout.activity_change_pw
) {

    private val vm : AccountInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newPass = binding.nowPw.text.toString()
        //vm.patchUserChangePassword(newPassword = newPass)
    }

    override fun initView() {

    }
}