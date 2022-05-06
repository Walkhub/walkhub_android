package com.semicolon.walkhub.ui.profile.setting.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyProfileBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(
    R.layout.activity_modify_profile
) {
    private val vm: ModifyProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchInfo()

        binding.image.setOnClickListener {

        }
        val name = binding.nameEt.text.toString()
        val file = File(binding.image.toString())
        val school = "1"

        binding.fixDoneBtn.setOnClickListener {

            vm.updateProfile(name = name, profileImage = file, schoolId = school)
        }

        binding.fixDoneBtn.setClickable(false)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: ModifyProfileViewModel.Event) = when (event) {
        is ModifyProfileViewModel.Event.FetchInfo -> {
            setProfileInfo(event.fetchInfoData)
        }

        is ModifyProfileViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        setTextWatcher()

        binding.back.setOnClickListener {
            finish()
        }

        binding.view3.setOnClickListener {
            val intent = Intent(context, SettingSearchSchoolActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setProfileInfo(fetchInfoData: FetchInfoEntity) {
        binding.mySchoolName.text = fetchInfoData.schoolName
        binding.name.text = fetchInfoData.name
        binding.classes.text = fetchInfoData.classNum.toString()
        binding.grade.text = fetchInfoData.grade.toString()
        fetchInfoData.profileImageUrl.let {
            if (it != null) {
                binding.image.loadCircleFromUrl(it)
            }
        }
    }

    private fun setTextWatcher() {
        binding.nameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.name.invisible()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.nameEt.text.isEmpty()) {
                    binding.name.visible()
                } else if (binding.nameEt.length() > 1 && binding.nameEt.length() < 11) {
                    binding.fixDoneBtn.background = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.bg_primary_button
                    )
                    binding.fixDoneBtn.setClickable(true)
                } else {
                    binding.fixDoneBtn.background = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.registerbuttondesign
                    )
                    binding.fixDoneBtn.setClickable(false)
                }
            }
        })
    }

}
