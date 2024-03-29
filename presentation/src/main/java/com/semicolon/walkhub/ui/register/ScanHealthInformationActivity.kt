package com.semicolon.walkhub.ui.register

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.semicolon.domain.enums.SexType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityScanHealthInformationBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.login.LoginActivity
import com.semicolon.walkhub.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class ScanHealthInformationActivity : BaseActivity<ActivityScanHealthInformationBinding>(
    R.layout.activity_scan_health_information
) {
    private var a: Boolean = false
    private var b by Delegates.notNull<Int>()

    private val vm: RegisterViewModel by viewModels()

    override fun initView() {
        setTextWatcher1()
        setTextWatcher2()

        binding.btContinue.setOnClickListener {
            notFinishRegister()
        }

        binding.btMan.setOnClickListener {
            changeGenderMan()
        }

        binding.btWo.setOnClickListener {
            changeGenderWo()
        }

        binding.constraint.setOnClickListener {
            hideKeyboard()
        }

        binding.tvLater.setOnClickListener {
            vm.setBody(Height = null, Weight = null)
            vm.register()
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: RegisterViewModel.Event) = when (event) {
        is RegisterViewModel.Event.SuccessRegister -> {
            val intent = Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

            showShortToast("회원가입을 성공적으로 완료하였습니다!")
        }
        else -> {

        }
    }


    private fun setTextWatcher1() {
        binding.etCm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changeContinue()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun setTextWatcher2() {
        binding.etkg.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changeContinue()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun changeContinue() {
        if (binding.etCm.length() > 0 && binding.etkg.length() > 0) {
            nextMethod()
        } else if (binding.etCm.length() < 1 || binding.etkg.length() < 1) {
            binding.btContinue.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.registerbuttondesign
            )
        }
    }

    private fun nextMethod() {
        when (a) {
            true -> {
                binding.btContinue.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.register_btn
                )
            }

            false -> {
                binding.btContinue.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.registerbuttondesign
                )
            }
        }
    }

    private fun notFinishRegister() {
        if (binding.etCm.length() < 1 || binding.etkg.length() < 1) {
            showLongToast("신체 정보를 올바르게 입력해주세요.")
        } else {
            finishRegister()
        }
    }

    private fun finishRegister() {
        when (a) {
            true -> {
                RegisterViewModel.sex = if (b < 1) {
                    SexType.MALE
                } else {
                    SexType.FEMALE
                }

                val height = binding.etCm.text.toString().toDouble()
                val weight = binding.etkg.text.toString().toInt()

                vm.setBody(height, weight)

                vm.register()
            }

            false -> {
                showShortToast("성별을 선택해주세요")
            }
        }
    }

    private fun changeGenderMan() {
        a = true
        b = 0

        changeContinue()
        binding.btMan.background =
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.register_btn
            )

        binding.btWo.background =
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.buttondesign
            )
    }

    private fun changeGenderWo() {
        a = true
        b = 1
        changeContinue()
        binding.btWo.background =
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.register_btn
            )

        binding.btMan.background =
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.buttondesign
            )
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etkg.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.etCm.windowToken, 0)
    }
}