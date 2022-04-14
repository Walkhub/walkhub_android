package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyProfileBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(
    R.layout.activity_modify_profile
) {

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
    }
}
