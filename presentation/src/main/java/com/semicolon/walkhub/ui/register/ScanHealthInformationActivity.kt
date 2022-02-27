package com.semicolon.walkhub.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityScanHealthInformationBinding
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.base.BaseActivity

class ScanHealthInformationActivity : BaseActivity<ActivityScanHealthInformationBinding>(
    R.layout.activity_scan_health_information
) {

    private var a: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btContinue.setOnClickListener {
            notFinishRegister()
        }

        binding.btMan.setOnClickListener {
            changeGenderMan()
        }

        binding.btWo.setOnClickListener {
            changeGenderWo()
        }

        binding.tvLater.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.constraint.setOnClickListener {
            hideKeyboard()
        }
    }

    override fun initView() {
        setTextWatcher1()
        setTextWatcher2()
    }

    private fun setTextWatcher1() {
        binding.eTcm.addTextChangedListener(object : TextWatcher {
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
        if (binding.eTcm.length() > 0 && binding.etkg.length() > 0) {
            nextMethod()
        } else if (binding.eTcm.length() < 1 || binding.etkg.length() < 1) {
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
        if (binding.eTcm.length() < 1 || binding.etkg.length() < 1) {
            showLongToast("신체 정보를 올바르게 입력해주세요.")
        } else {
            finishRegister()
        }
    }

    private fun finishRegister() {
        when (a) {
            true -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            false -> {
                showShortToast("성별을 선택해주세요")
            }
        }
    }

    private fun changeGenderMan() {
        a = true
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
        imm.hideSoftInputFromWindow(binding.eTcm.windowToken, 0)
    }
}