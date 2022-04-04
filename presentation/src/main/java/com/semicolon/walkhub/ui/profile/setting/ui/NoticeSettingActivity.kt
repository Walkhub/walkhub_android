package com.semicolon.walkhub.ui.profile.setting.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.ToggleSwitch
import com.semicolon.walkhub.databinding.ActivityNoticeSettingBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class NoticeSettingActivity : BaseActivity<ActivityNoticeSettingBinding>(
    R.layout.activity_notice_setting
) {

    override fun initView() {
        switch()

    }


    fun ComposeView.setToggleSwitch(
        onToggleOn: () -> Unit,
        onToggleOff: () -> Unit
    ) = this.apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ToggleSwitch(
                Modifier.padding(vertical = 16.dp),
                onToggleOn = { onToggleOn() },
                onToggleOff = { onToggleOff() }
            )
        }
    }


    private fun switch() {
        binding.cheerupSwt.setToggleSwitch(
            onToggleOn = {},
            onToggleOff = {}
        )

        binding.noticeSwt.setToggleSwitch(
            onToggleOn = {},
            onToggleOff = {}
        )

        binding.recommendSwt.setToggleSwitch(
            onToggleOn = {},
            onToggleOff = {}
        )
        binding.challengeGoalSwt.setToggleSwitch(
            onToggleOn = {},
            onToggleOff = {}
        )
        binding.challengeEndSwt.setToggleSwitch(
            onToggleOn = {},
            onToggleOff = {}
        )
    }
}