package com.semicolon.walkhub.ui.profile.setting.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyProfileBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.register.adapter.SearchSchoolAdapter
import com.semicolon.walkhub.ui.register.model.SecondSearchSchoolData
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyProfileViewModel
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

    private fun handleEvent(event: ModifyProfileViewModel.Event) = when (event) {
        is ModifyProfileViewModel.Event.FetchInfo -> {
            setProfileInfo(event.fetchInfoData)
        }

        is ModifyProfileViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }

        binding.view3.setOnClickListener {
            val intent = Intent(context, SettingSearchSchoolActivity::class.java)
            startActivity(intent)
        }

        binding.nameEt.setOnTouchListener { _: View, event: MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.name.invisible()
                }
            }
            false
        }
    }
    private fun setProfileInfo(fetchInfoData: FetchInfoEntity) {
        binding.mySchoolName.text = fetchInfoData.schoolName
        binding.name.text = fetchInfoData.name
        binding.classes.text = fetchInfoData.classNum.toString()
        binding.grade.text = fetchInfoData.grade.toString()
        fetchInfoData.profileImageUrl.let { binding.image.loadCircleFromUrl(it) }
    }

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
