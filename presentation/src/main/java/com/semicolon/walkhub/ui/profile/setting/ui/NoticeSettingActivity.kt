package com.semicolon.walkhub.ui.profile.setting.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import com.semicolon.domain.entity.notification.NotificationStatusEntity
import com.semicolon.domain.enums.NotificationType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.ToggleState
import com.semicolon.walkhub.customview.ToggleSwitch
import com.semicolon.walkhub.databinding.ActivityNoticeSettingBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.NoticeSettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeSettingActivity : BaseActivity<ActivityNoticeSettingBinding>(
    R.layout.activity_notice_setting
) {

    private val vm: NoticeSettingViewModel by viewModels()

    var data: Int = 0

    private var noticeToggleState = ToggleState.TOGGLE_OFF
    private var expirationToggleState = ToggleState.TOGGLE_OFF
    private var successToggleState = ToggleState.TOGGLE_OFF
    private var challengeToggleState = ToggleState.TOGGLE_OFF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        switch()
        data = intent.getIntExtra("user_id", data)

        vm.notificationStatus()


        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: NoticeSettingViewModel.Event) = when (event) {
        is NoticeSettingViewModel.Event.WhetherNotification -> {
            val data = event.notificationStatusEntity

            for(topicWhether in data.topicWhetherList) {
                when(topicWhether.title){
                    NotificationType.NOTICE -> {
                        noticeToggleState = if(topicWhether.isSubscribe) {
                            ToggleState.TOGGLE_ON
                        } else {
                            ToggleState.TOGGLE_OFF
                        }
                    }
                    NotificationType.CHALLENGE_EXPIRATION -> {
                        expirationToggleState = if(topicWhether.isSubscribe) {
                            ToggleState.TOGGLE_ON
                        } else {
                            ToggleState.TOGGLE_OFF
                        }
                    }
                    NotificationType.CHALLENGE_SUCCESS -> {
                        successToggleState = if(topicWhether.isSubscribe) {
                            ToggleState.TOGGLE_ON
                        } else {
                            ToggleState.TOGGLE_OFF
                        }
                    }
                    NotificationType.CHALLENGE -> {
                        challengeToggleState = if(topicWhether.isSubscribe) {
                            ToggleState.TOGGLE_ON
                        } else {
                            ToggleState.TOGGLE_OFF
                        }
                    }
                }
            }
        }

        is NoticeSettingViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }

    }


    private fun ComposeView.setToggleSwitch(
        onToggleOn: () -> Unit,
        onToggleOff: () -> Unit,
        defaultState: ToggleState,
    ) = this.apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            ToggleSwitch(
                Modifier.padding(vertical = 16.dp),
                onToggleOn = { onToggleOn() },
                onToggleOff = { onToggleOff() },
                defaultState = defaultState,
            )
        }
    }


    private fun switch() {
        binding.cheerupSwt.setToggleSwitch(
            onToggleOn = {},
            onToggleOff = {},
            defaultState = ToggleState.TOGGLE_ON
        )

        binding.noticeSwt.setToggleSwitch(
            onToggleOn = { vm.patchSwitchOn(userId = data, type = NotificationType.NOTICE) },
            onToggleOff = { vm.patchSwitchOff(userId = data, type = NotificationType.NOTICE) },
            defaultState = noticeToggleState
        )

        binding.recommendSwt.setToggleSwitch(
            onToggleOn = { vm.patchSwitchOn(userId = data, type = NotificationType.CHALLENGE) },
            onToggleOff = { vm.patchSwitchOff(userId = data, type = NotificationType.CHALLENGE) },
            defaultState = challengeToggleState
        )
        binding.challengeGoalSwt.setToggleSwitch(
            onToggleOn = {
                vm.patchSwitchOn(
                    userId = data,
                    type = NotificationType.CHALLENGE_SUCCESS
                )
            },
            onToggleOff = {
                vm.patchSwitchOff(userId = data,
                    type = NotificationType.CHALLENGE_SUCCESS)
            },
            defaultState = successToggleState
        )
        binding.challengeEndSwt.setToggleSwitch(
            onToggleOn = {
                vm.patchSwitchOn(
                    userId = data,
                    type = NotificationType.CHALLENGE_EXPIRATION
                )
            },
            onToggleOff = {
                vm.patchSwitchOff(userId = data,
                    type = NotificationType.CHALLENGE_EXPIRATION)
            },
            defaultState = expirationToggleState
        )
    }
}