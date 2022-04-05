package com.semicolon.walkhub.ui.profile.setting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.semicolon.domain.entity.users.FetchAuthInfoEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentAccountInfoBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.viewmodel.profile.setting.SettingViewModel

class AccountInfoFragment : BaseFragment<FragmentAccountInfoBinding>(
    R.layout.fragment_account_info
) {

    private val vm: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.fetchAuthInfo()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: SettingViewModel.Event) = when (event) {
        is SettingViewModel.Event.FetchAuthInfo -> {
            setAuthInfo(event.fetchAuthInfoData)
        }

        is SettingViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    private fun setAuthInfo(fetchAuthInfoData: FetchAuthInfoEntity) {
        binding.idInfo.text = fetchAuthInfoData.accountId
        binding.phoneInfo.text = fetchAuthInfoData.phoneNumber
    }


    override fun initView() {
    }
}