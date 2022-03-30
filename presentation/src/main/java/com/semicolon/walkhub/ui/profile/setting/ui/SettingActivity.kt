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
            val bundle = Bundle()
            val fragmentA = AccountInfoFragment()
            fragmentA.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.account_info_fragment, fragmentA)
            transaction.commit()
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
