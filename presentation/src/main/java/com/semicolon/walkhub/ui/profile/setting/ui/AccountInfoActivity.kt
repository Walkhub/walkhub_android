package com.semicolon.walkhub.ui.profile.setting.ui

import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityAccountInfoBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class AccountInfoActivity : BaseActivity<ActivityAccountInfoBinding>(
    R.layout.activity_account_info
) {
    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
    }
}