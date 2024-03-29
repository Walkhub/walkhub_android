package com.semicolon.walkhub.ui.profile.setting.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyProfileBinding
import com.semicolon.walkhub.extensions.UrlConverter
import com.semicolon.walkhub.extensions.fetchImage
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyProfileViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(
    R.layout.activity_modify_profile
) {
    companion object {
        var destroyProfileImage: File? = null
    }

    private val vm: ModifyProfileViewModel by viewModels()

    var schoolId: Long = 0
    var data: Int = 0
    var schoolName: String = ""
    var school: String = ""

    private var profileImage: File? = null

    private var ivProfile: File? = null

    private var oldSchoolId: Long = 0

    private var name: String? = null

    private var temp = false

    @Inject
    lateinit var urlConverter: UrlConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchInfo()

        CoroutineScope(Dispatchers.IO).launch {
            profileImage = intent.getStringExtra("profile_image")?.let {
                UrlConverter(applicationContext).convert(it)
            }
        }

        oldSchoolId = intent.getLongExtra("school_id", oldSchoolId)

        binding.image.setOnClickListener {
            setNormalSingleButton()
        }

        binding.fixDoneBtn.setOnClickListener {
            patchProfileInfo()
        }

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

        is ModifyProfileViewModel.Event.Success -> {
            showShortToast("프로필 수정을 완료하였습니다!")
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        setTextWatcher()
    }

    override fun onDestroy() {
        super.onDestroy()

        destroyProfileImage = profileImage
    }

    override fun initView() {
        binding.back.setOnClickListener {
            finish()
        }

        temp = intent.getBooleanExtra("next", temp)
        if (temp) {

            schoolId = intent.getLongExtra("data", schoolId)
            school = intent.getStringExtra("school").toString()
            binding.myChangeSchoolName.text = school
            schoolDesign()
            btnBackTrue()
            return

        }

        binding.view3.setOnClickListener {
            val intent = Intent(context, SettingSearchSchoolActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setNormalSingleButton() {
        TedImagePicker.with(this)
            .mediaType(MediaType.IMAGE)
            .start { uri ->
                showSingleImage(uri)
                ivProfile = uri.toFile()
            }
    }

    private fun showSingleImage(uri: Uri) {
        Glide.with(this).load(uri).into(binding.image)
        suspend { fetchImage(context).let { uri.toFile().toString() } }
    }

    private fun setProfileInfo(fetchInfoData: FetchInfoEntity) {
        binding.mySchoolName.text = fetchInfoData.schoolName
        name = fetchInfoData.name
        binding.name.text = name
        fetchInfoData.classNum.toString().let {
            binding.gradeClass.visible()
            binding.classes.invisible()
            binding.textClass.invisible()
            if (it != "0") {
                binding.classes.text = fetchInfoData.classNum.toString()
                binding.gradeClass.invisible()
                binding.classes.visible()
                binding.textClass.visible()
            }

        }
        fetchInfoData.profileImageUrl.let {
            if (it != null) {
                binding.image.loadCircleFromUrl(it)
            }
        }

        fetchInfoData.grade.toString().let {
            binding.textGrade.invisible()
            binding.grade.invisible()
            if (it != "0") {
                binding.grade.text = fetchInfoData.grade.toString()
                binding.grade.visible()
                binding.textGrade.visible()
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
                } else if (binding.nameEt.length() > 1) {
                    btnBackTrue()
                }
            }

        })
    }

    private fun schoolDesign() {
        binding.myChangeSchoolName.visible()
        binding.mySchoolName.invisible()
        binding.grade.invisible()
        binding.classes.invisible()
        binding.textClass.invisible()
        binding.textGrade.invisible()
        binding.gradeClass.visible()
    }

    private fun btnBackTrue() {
        binding.fixDoneBtn.background = ContextCompat.getDrawable(
            applicationContext,
            R.drawable.bg_primary_button
        )
        binding.fixDoneBtn.isClickable = true
    }

    private fun btnBackFalse() {
        binding.fixDoneBtn.background = ContextCompat.getDrawable(
            applicationContext,
            R.drawable.registerbuttondesign
        )
        binding.fixDoneBtn.isClickable = false
    }

    private fun patchProfileInfo() {
        when {
            //학교만 보낼
            binding.nameEt.length() < 1 && profileImage == null && binding.myChangeSchoolName.length() > 1 -> {
                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(name = name.toString(),
                        profileImage = destroyProfileImage,
                        schoolId = schoolId)
                    vm.deleteClass()
                }).callDialog()
            }
            //이름만 보낼 때
            binding.nameEt.length() > 1 && ivProfile == null && binding.myChangeSchoolName.length() < 1 -> {
                name = binding.nameEt.text.toString()
                vm.updateProfile(name = name.toString(),
                    profileImage = profileImage,
                    schoolId = oldSchoolId)
            }
            //프사만 보낼 때
            binding.nameEt.length() < 1 && binding.myChangeSchoolName.length() < 1 && profileImage != null -> {
                vm.updateProfile(name = name.toString(),
                    profileImage = ivProfile,
                    schoolId = oldSchoolId)
            }
            //이름, 학교 보낼 때
            binding.nameEt.length() > 1 && binding.myChangeSchoolName.length() > 1 && profileImage == null -> {
                name = binding.nameEt.text.toString()
                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(
                        name = name.toString(),
                        profileImage = destroyProfileImage,
                        schoolId = schoolId)
                    vm.deleteClass()
                }).callDialog()
            }
            //이름, 프사 보낼 때
            binding.nameEt.length() > 1 && binding.myChangeSchoolName.length() < 1 && profileImage != null -> {
                name = binding.nameEt.text.toString()
                vm.updateProfile(name = name.toString(),
                    profileImage = ivProfile,
                    schoolId = oldSchoolId)
            }
            //학교, 프사 보낼 때
            binding.nameEt.length() < 1 && binding.myChangeSchoolName.length() > 1 && ivProfile != null -> {
                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(name = name.toString(),
                        profileImage = ivProfile,
                        schoolId = schoolId)
                    vm.deleteClass()
                }).callDialog()
            }
            //모두 보낼 때
            binding.nameEt.length() > 1 && binding.myChangeSchoolName.length() > 1 && profileImage != null -> {
                name = binding.nameEt.text.toString()
                val schoolId: Long = schoolId
                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(name = name.toString(),
                        schoolId = schoolId,
                        profileImage = destroyProfileImage)
                    vm.deleteClass()
                }).callDialog()
            }
        }
    }
}