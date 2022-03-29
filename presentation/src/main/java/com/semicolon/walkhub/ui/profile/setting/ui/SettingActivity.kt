package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySettingBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class SettingActivity : BaseActivity<ActivitySettingBinding>(
    R.layout.activity_setting
) {

    override fun initView() {
        binding.modifyProfileInfo.setOnClickListener {
            val intent = Intent(context, ModifyProfileActivity::class.java)
            startActivity(intent)
        }

        binding.modifyHealthInfo.setOnClickListener {
            val bundle = Bundle()
            val fragmentA = ModifyHealthInfoFragment()
            fragmentA.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.modify_health_info_cl, fragmentA)
            transaction.commit()
        }
        binding.modifyLoginInfo.setOnClickListener {
        }

        binding.notificationSetting.setOnClickListener {
            val intent = Intent(context, NoticeSettingActivity::class.java)
            startActivity(intent)
        }

        binding.ask.setOnClickListener {

        }
        binding.versionInfo.setOnClickListener {

        }
        binding.logout.setOnClickListener {

        }
    }
}
