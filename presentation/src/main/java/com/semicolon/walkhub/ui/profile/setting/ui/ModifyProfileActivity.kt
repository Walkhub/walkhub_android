package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyProfileBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.register.SearchSchoolActivity

class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(
    R.layout.activity_modify_profile
) {

    override fun initView() {
        binding.view3.setOnClickListener {
            val intent = Intent(context, SearchSchoolActivity::class.java)
            startActivity(intent)
        }
    }
}
