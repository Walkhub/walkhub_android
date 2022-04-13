package com.semicolon.walkhub.ui.profile.setting.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.semicolon.domain.entity.users.FetchUserHealthEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyHealthInfoBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyHealthInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModifyHealthInfoActivity : BaseActivity<ActivityModifyHealthInfoBinding>(
    R.layout.activity_modify_health_info
) {
    private val vm : ModifyHealthInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchUserHealth()

        binding.modifyDone.setOnClickListener {
            vm.patchUserHealth(height = binding.editHeight.text.toString().toDouble(), weight = binding.editWeight.text.toString().toInt(), sex = String() )
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

    }
    private fun handleEvent(event: ModifyHealthInfoViewModel.Event) = when (event) {
        is ModifyHealthInfoViewModel.Event.FetchUserHealth -> {
            setHealthInfo(event.fetchUserHealthData)
        }

        is ModifyHealthInfoViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun setHealthInfo(fetchUserHealthData: FetchUserHealthEntity) {
        binding.weight.text = fetchUserHealthData.weight.toString()
        binding.height.text = fetchUserHealthData.height.toString()

    }
}