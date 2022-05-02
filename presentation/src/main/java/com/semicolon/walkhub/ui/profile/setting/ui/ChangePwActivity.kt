package com.semicolon.walkhub.ui.profile.setting.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.semicolon.domain.param.user.VerifyPasswordParam
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityChangePwBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.ChangePwViewModel
import com.semicolon.walkhub.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class ChangePwActivity : BaseActivity<ActivityChangePwBinding>(
    R.layout.activity_change_pw
) {
    private val vm: ChangePwViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.goNextBtn.setOnClickListener {
            val password = binding.nowPw.text.toString()
            vm.verifyPassword(verifyPasswordParam = VerifyPasswordParam(password))
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: ChangePwViewModel.Event) = when (event) {

        is ChangePwViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
        is ChangePwViewModel.Event.SuccessVerify -> {
            val intent = Intent(this, RealChangePwActivity::class.java)
            val password = binding.nowPw.text.toString()
            intent.putExtra("pw", password)
            startActivity(intent)

        }
    }

    override fun initView() {
        setTextWatcher()

        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun setTextWatcher() {
        binding.nowPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.nowPw.setClickable(true)
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val password = binding.nowPw.text.toString()

                if (password.length > 7) {
                    val passwordPattern =
                        "^.*(?=^.{8,30}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"

                    val pt = Pattern.matches(passwordPattern, password)

                    if (pt) {
                        binding.goNextBtn.background = ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.bg_primary_button
                        )
                        binding.goNextBtn.setClickable(true)
                    } else {
                        showShortToast("비밀번호에는의 한개 이상 숫자와 특수문자가 포함되어야합니다.")
                        binding.goNextBtn.background = ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.registerbuttondesign
                        )
                        binding.goNextBtn.setClickable(false)
                    }
                }
            }
        })
    }
}
