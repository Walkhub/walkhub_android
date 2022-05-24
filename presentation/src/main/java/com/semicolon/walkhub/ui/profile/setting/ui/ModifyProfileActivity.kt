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
    private val vm: ModifyProfileViewModel by viewModels()

    var schoolId: Long = 0
    var data: Int = 0
    var schoolName: String = ""
    var school: String = ""
    var name: String = ""

    private var profileImage: File? = null

    private var oldSchoolId: Long = 0

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

    override fun onStart() {
        super.onStart()

        name = intent.getStringExtra("name").toString()
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

        binding.back.setOnClickListener {
            finish()
        }

        binding.view3.setOnClickListener {
            val intent = Intent(context, SettingSearchSchoolActivity::class.java).apply {
                putExtra("name", name)
                putExtra("image", profileImage)
            }
            startActivity(intent)
        }
    }

    private fun setNormalSingleButton() {
        TedImagePicker.with(this)
            .mediaType(MediaType.IMAGE)
            .start { uri ->
                showSingleImage(uri)
                profileImage = uri.toFile()
            }
    }

    private fun showSingleImage(uri: Uri) {
        Glide.with(this).load(uri).into(binding.image)

        suspend { fetchImage(context).let { uri.toFile().toString() } }
    }

    private fun setProfileInfo(fetchInfoData: FetchInfoEntity) {
        binding.mySchoolName.text = fetchInfoData.schoolName
        binding.name.text = fetchInfoData.name
        fetchInfoData.classNum.toString().let {
            binding.gradeClass.visible()
            binding.classes.invisible()
            binding.textClass.invisible()
            if (it != null) {
                binding.classes.text
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
            if (it != null) {
                binding.grade.text
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
            //학교만 보낼때
            binding.nameEt.length() < 1 && profileImage == null && binding.myChangeSchoolName.length() > 1 -> {
                val name = binding.name.text.toString()

                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(name = name,
                        profileImage = profileImage,
                        schoolId = schoolId)
                    vm.deleteClass()
                }).callDialog()

                showShortToast("학교")
            }
            //이름만 보낼 때
            binding.nameEt.length() > 1 && profileImage == null && binding.myChangeSchoolName.length() < 1 -> {
                val name = binding.nameEt.text.toString()
                vm.updateProfile(name = name,
                    profileImage = profileImage,
                    schoolId = oldSchoolId)
            }
            //프사만 보낼 때
            binding.nameEt.length() < 1 && binding.myChangeSchoolName.length() < 1 && profileImage != null -> {
                val name = binding.name.text.toString()
                vm.updateProfile(name = name, profileImage = profileImage, schoolId = oldSchoolId)
                showShortToast("프사")
            }
            //이름, 학교 보낼 때
            binding.nameEt.length() > 1 && binding.myChangeSchoolName.length() > 1 && profileImage == null -> {
                val name = binding.nameEt.text.toString()
                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(name = name,
                        profileImage = profileImage,
                        schoolId = schoolId)
                    vm.deleteClass()
                }).callDialog()
                showShortToast("이름, 학교")
            }
            //이름, 프사 보낼 때
            binding.nameEt.length() > 1 && binding.myChangeSchoolName.length() < 1 && profileImage != null -> {
                val name = binding.nameEt.text.toString()
                vm.updateProfile(name = name, profileImage = profileImage, schoolId = oldSchoolId)
                showShortToast("이름, 프사")
            }
            //학교, 프사 보낼 때
            binding.nameEt.length() < 1 && binding.myChangeSchoolName.length() > 1 && profileImage != null -> {
                val name = binding.name.text.toString()
                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(name = name, profileImage = profileImage, schoolId = schoolId)
                    vm.deleteClass()
                }).callDialog()

                showShortToast("학교, 프사")
            }
            //모두 보낼 때
            binding.nameEt.length() > 1 && binding.myChangeSchoolName.length() > 1 && profileImage != null -> {
                val name = binding.nameEt.text.toString()
                val schoolId: Long = schoolId
                SchoolDialog(this, onYesClick = {
                    vm.updateProfile(name = name, schoolId = schoolId, profileImage = profileImage)
                    vm.deleteClass()
                }).callDialog()

                showShortToast("모두 보냄")
            }


        }
    }
}