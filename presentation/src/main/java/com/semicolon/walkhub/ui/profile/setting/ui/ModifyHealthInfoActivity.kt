package com.semicolon.walkhub.ui.profile.setting.ui

import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyHealthInfoBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class ModifyHealthInfoActivity : BaseActivity<ActivityModifyHealthInfoBinding>(
    R.layout.activity_modify_health_info
) {

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
    }
}