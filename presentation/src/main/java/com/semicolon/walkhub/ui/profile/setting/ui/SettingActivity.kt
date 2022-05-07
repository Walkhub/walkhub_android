package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySettingBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.HomeActivity
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.hub.model.SearchUserData
import com.semicolon.walkhub.ui.profile.model.MyPageData
import com.semicolon.walkhub.ui.register.model.SecondSearchSchoolData
import com.semicolon.walkhub.viewmodel.profile.setting.NoticeSettingViewModel.Companion.userId
import com.semicolon.walkhub.viewmodel.profile.setting.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class SettingActivity() : BaseActivity<ActivitySettingBinding>(
    R.layout.activity_setting
) {

    private val vm: SettingViewModel by viewModels()

    private var id : Int = 1
    private var userId by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = intent.getIntExtra("user_id", id)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: SettingViewModel.Event) {
        when (event) {
            is SettingViewModel.Event.Success -> {
                val intent = Intent(context, HomeActivity::class.java)
                finishAffinity()
                startActivity(intent)
            }

            is SettingViewModel.Event.Failed -> {
                showShortToast(event.message)
            }
        }
    }

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
        binding.modifyProfileInfo.setOnClickListener {
            val intent = Intent(context, ModifyProfileActivity::class.java)
            startActivity(intent)
        }

        binding.modifyHealthInfo.setOnClickListener {
            val intent = Intent(context, ModifyHealthInfoActivity::class.java)
            startActivity(intent)
        }
        binding.modifyLoginInfo.setOnClickListener {
            val intent = Intent(context, AccountInfoActivity::class.java)
            startActivity(intent)
        }

        binding.notificationSetting.setOnClickListener {
            val intent = Intent(context, NoticeSettingActivity::class.java)
            intent.putExtra("user_id", userId)
            intent.putExtra("movePage", true)
            startActivity(intent)
        }

        binding.ask.setOnClickListener {
            //TODO: 문의하기 디자인이 추가되야됨
        }
        binding.versionInfo.setOnClickListener {
            //TODO: 버젼정보
        }
        binding.logout.setOnClickListener {
            vm.logOut()
        }
    }
}
