package com.semicolon.walkhub.ui.profile.setting

import android.content.Intent
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySettingBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.base.BaseFragment

class SettingActivity : BaseActivity<ActivitySettingBinding>(
    R.layout.activity_setting
) {

    override fun initView() {
        binding.modifyProfileInfo.setOnClickListener {
            val intent = Intent(context, ModifyProfileActivity::class.java)
            startActivity(intent)
        }

        binding.modifyHealthInfo.setOnClickListener {
           /* val fragment = ModifyHealthInfoFragment()
            supportFragmentManager()
                .beginTransaction()
                .add(R.id.onfragment, fragment)
                .commit()*/
        }
        binding.modifyLoginInfo.setOnClickListener {
        }

        binding.notificationSetting.setOnClickListener {
            //val intent = Intent(context, NoticeSettingActivity::class.java)
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
