package com.semicolon.walkhub.ui.profile.setting.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyProfileBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyProfileViewModel
import com.semicolon.walkhub.viewmodel.profile.setting.NoticeSettingViewModel
import gun0912.tedimagepicker.builder.TedImagePicker

class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(
    R.layout.activity_modify_profile
) {
    private val vm: ModifyProfileViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //vm.fetchInfo()
        binding.image.setOnClickListener {
            setNormalSingleButton()
        }


        /*repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }*/
    }

    /*private fun handleEvent(event: ModifyProfileViewModel.Event) = when (event) {
        is ModifyProfileViewModel.Event.FetchInfo -> {
            setProfileInfo(event.fetchInfoData)
        }

        is ModifyProfileViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }*/

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
    }

    /*private fun setProfileInfo(fetchInfoData: FetchInfoEntity) {
        binding.mySchoolName.text = fetchInfoData.schoolName
        binding.name.text = fetchInfoData.name
        binding.classes.text = fetchInfoData.classNum.toString()
        binding.grade.text = fetchInfoData.grade.toString()
        fetchInfoData.profileImageUrl.let { binding.image.loadCircleFromUrl(it) }
    }*/

    private fun setNormalSingleButton() {
        binding.image.setOnClickListener {
            TedImagePicker.with(this)
                .start { uri -> showSingleImage(uri) }
            binding.hsvImage.visibility = View.VISIBLE
        }
    }

    private fun showSingleImage(uri: Uri) {
        binding.hsvImage.visibility = View.GONE
        Glide.with(this).load(uri).into(binding.image)

    }

}
