package com.semicolon.walkhub.ui.profile.setting

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.ToggleSwitch
import com.semicolon.walkhub.databinding.ActivityNoticeSettingBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class NoticeSettingActivity : BaseActivity<ActivityNoticeSettingBinding>(
    R.layout.activity_notice_setting
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_setting)

    }

    override fun initView() {

    }
}