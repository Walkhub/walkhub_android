package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySettingBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


class SettingActivity : BaseActivity<ActivitySettingBinding>(
    R.layout.activity_setting
) {

    override fun initView() {
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
            startActivity(intent)
        }

        binding.ask.setOnClickListener {
            //TODO: 문의하기 디자인이 추가되야됨
        }
        binding.versionInfo.setOnClickListener {
            //TODO: 버젼정보
        }
        binding.logout.setOnClickListener {
            //TODO: 로그아웃은 뷰모델 나오고
        }
    }
}
