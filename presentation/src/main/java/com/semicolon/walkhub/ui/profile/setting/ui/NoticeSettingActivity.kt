package com.semicolon.walkhub.ui.profile.setting.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import com.semicolon.domain.enums.NotificationType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.ToggleSwitch
import com.semicolon.walkhub.databinding.ActivityNoticeSettingBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.NoticeSettingViewModel
import com.semicolon.walkhub.viewmodel.register.RegisterViewModel
import kotlin.properties.Delegates

class NoticeSettingActivity : BaseActivity<ActivityNoticeSettingBinding>(
    R.layout.activity_notice_setting
) {

    private val vm: NoticeSettingViewModel by viewModels()

    var user: Int = 0
    var data: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = intent.getIntExtra("user_id",user)
        NoticeSettingViewModel.userId = data
    }

    override fun initView() {
        switch()
        binding.back.setOnClickListener {
            finish()
        }

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
            onToggleOn = { vm.patchSwitchOn(userId = user, type = NotificationType.NOTICE) },
            onToggleOff = { vm.patchSwitchOff(type = NotificationType.NOTICE) }
        )

        binding.recommendSwt.setToggleSwitch(
            onToggleOn = { vm.patchSwitchOn(userId = user, type = NotificationType.CHALLENGE) },
            onToggleOff = { vm.patchSwitchOff(type = NotificationType.CHALLENGE) }
        )
        binding.challengeGoalSwt.setToggleSwitch(
            onToggleOn = {
                vm.patchSwitchOn(
                    userId = user,
                    type = NotificationType.CHALLENGE_SUCCESS
                )
            },
            onToggleOff = { vm.patchSwitchOff(type = NotificationType.CHALLENGE_SUCCESS) }
        )
        binding.challengeEndSwt.setToggleSwitch(
            onToggleOn = {
                vm.patchSwitchOn(
                    userId = user,
                    type = NotificationType.CHALLENGE_EXPIRATION
                )
            },
            onToggleOff = { vm.patchSwitchOff(type = NotificationType.CHALLENGE_EXPIRATION) }
        )
    }
}