package com.semicolon.walkhub.ui.profile

import android.content.Intent
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentProfileBinding
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.profile.setting.ui.SettingActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    R.layout.fragment_profile
) {
    override fun initView() {
        binding.setting.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}