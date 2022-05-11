package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.semicolon.domain.entity.users.FetchAuthInfoEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityAccountInfoBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.HomeActivity
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.register.ui.PrivacyActivity
import com.semicolon.walkhub.ui.register.ui.ServiceInstructionActivity
import com.semicolon.walkhub.viewmodel.profile.setting.AccountInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountInfoActivity : BaseActivity<ActivityAccountInfoBinding>(
    R.layout.activity_account_info
) {
    private val vm: AccountInfoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchAuthInfo()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: AccountInfoViewModel.Event) = when (event) {
        is AccountInfoViewModel.Event.FetchAuthInfo -> {
            setAuthInfo(event.fetchAuthInfoData)
        }

        is AccountInfoViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
        binding.imDie.setOnClickListener {
            vm.deleteAccount()
            val intent = Intent(this, HomeActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }
        binding.serviceTerms.setOnClickListener {
            val intent = Intent(this, ServiceInstructionActivity::class.java)
            startActivity(intent)
        }
        binding.handlingPolicy.setOnClickListener {
            val intent = Intent(this, PrivacyActivity::class.java)
            startActivity(intent)
        }
        binding.changePassword.setOnClickListener {
            //TODO: 아직 디자인이 없음....
        }
    }

    private fun setAuthInfo(fetchAuthInfoData: FetchAuthInfoEntity) {
        binding.idInfo.text = fetchAuthInfoData.accountId
        binding.phoneInfo.text = fetchAuthInfoData.phoneNumber
    }
}