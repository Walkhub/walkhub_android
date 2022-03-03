package com.semicolon.walkhub.ui.profile.setting

import android.os.Bundle
import androidx.compose.ui.platform.ViewCompositionStrategy
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
        switch()
    }

    private fun switch() {
        binding.cheerupSwt.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ToggleSwitch(
                    onToggleOn = {},
                    onToggleOff = {}
                )
            }
        }

        binding.noticeSwt.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ToggleSwitch(
                    onToggleOn = {},
                    onToggleOff = {}
                )
            }
        }

        binding.recommendSwt.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ToggleSwitch(
                    onToggleOn = {},
                    onToggleOff = {}
                )
            }
        }
        binding.challengeGoalSwt.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ToggleSwitch(
                    onToggleOn = {},
                    onToggleOff = {}
                )
            }
        }
        binding.challengeEndSwt.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ToggleSwitch(
                    onToggleOn = {},
                    onToggleOff = {}
                )
            }
        }
    }
}