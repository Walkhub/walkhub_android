package com.semicolon.walkhub.ui.profile.setting.ui

import android.os.Bundle
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityChangePwBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePwActivity:  BaseActivity<ActivityChangePwBinding>(
    R.layout.activity_change_pw
){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
    }
}