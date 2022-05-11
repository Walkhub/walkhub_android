package com.semicolon.walkhub.ui.profile.setting.ui

<<<<<<< HEAD
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
=======
import android.os.Bundle
import androidx.activity.viewModels
>>>>>>> develop
import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyProfileBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
<<<<<<< HEAD
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
=======
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyProfileViewModel
>>>>>>> develop

@AndroidEntryPoint
class ModifyProfileActivity : BaseActivity<ActivityModifyProfileBinding>(
    R.layout.activity_modify_profile
) {
    private val vm: ModifyProfileViewModel by viewModels()

<<<<<<< HEAD
    var schoolId: Int = 0
    var data: Int = 0
    var schoolName: String = ""
    var school: String = ""

    private var temp = false

=======
>>>>>>> develop
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchInfo()

<<<<<<< HEAD
        binding.image.setOnClickListener {

        }

        val name = binding.nameEt.text.toString()
        val file = File(binding.image.toString())
        val schoolId = schoolId.toLong()

        binding.fixDoneBtn.setOnClickListener {

            vm.updateProfile(name = name, profileImage =    file, schoolId = schoolId)

        }

        binding.fixDoneBtn.isClickable = false

=======
>>>>>>> develop
        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: ModifyProfileViewModel.Event) = when (event) {
        is ModifyProfileViewModel.Event.FetchInfo -> {
<<<<<<< HEAD
            setProfileInfo(event.fetchInfoData)
=======
            setInfo(event.fetchInfoData)
>>>>>>> develop
        }

        is ModifyProfileViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }
<<<<<<< HEAD
=======

>>>>>>> develop

    override fun initView() {
        temp = intent.getBooleanExtra("next", temp)
        if (temp) {
            data = intent.getIntExtra("data", schoolId)
            schoolId = data
            school = intent.getStringExtra("school").toString()
            binding.myChangeSchoolName.text = school
            schoolDesign()
            return
        }
        setTextWatcher()

        binding.back.setOnClickListener {
            finish()
        }

        binding.view3.setOnClickListener {
            val intent = Intent(context, SettingSearchSchoolActivity::class.java)
            startActivity(intent)
        }
    }

<<<<<<< HEAD
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
                when {
                    binding.nameEt.text.isEmpty() -> {
                        binding.name.visible()
                    }
                    binding.nameEt.length() in 2..10 -> {
                        binding.fixDoneBtn.background = ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.bg_primary_button
                        )
                        binding.fixDoneBtn.isClickable = true
                    }
                    else -> {
                        binding.fixDoneBtn.background = ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.registerbuttondesign
                        )
                        binding.fixDoneBtn.isClickable = false
                    }
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

        if (binding.myChangeSchoolName.text.isNotEmpty() && binding.myChangeSchoolName.text.length > 1) {
            binding.fixDoneBtn.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.bg_primary_button
            )
            binding.fixDoneBtn.isClickable = true
        } else {
            binding.fixDoneBtn.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.registerbuttondesign
            )
            binding.fixDoneBtn.isClickable = false
        }
    }

=======
    private fun setInfo(fetchInfoData: FetchInfoEntity){
        fetchInfoData.profileImageUrl.let { binding.image.loadCircleFromUrl(it) }
        binding.grade.text = fetchInfoData.grade.toString()
        binding.classes.text = fetchInfoData.classNum.toString()
        binding.mySchoolName.text = fetchInfoData.schoolName
    }
>>>>>>> develop
}
