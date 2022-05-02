package com.semicolon.walkhub.ui.profile.setting.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityRealChangePwBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.profile.setting.RealChangePwViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class RealChangePwActivity : BaseActivity<ActivityRealChangePwBinding>(
    R.layout.activity_real_change_pw
) {

    private val vm: RealChangePwViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.changeBtn.setOnClickListener {
            val intent = getIntent()
            val password = intent.getStringExtra("pw")
            val newPass = binding.nowPw.text.toString()
            if (password != null) {
                vm.patchUserChangePassword(
                    patchUserChangePasswordParam = PatchUserChangePasswordParam(
                        password,
                        newPass
                    )
                )
            }
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: RealChangePwViewModel.Event) = when (event) {

        is RealChangePwViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
        RealChangePwViewModel.Event.SuccessChange -> {
            showShortToast("비밀번호 변경을 완료했어요.")
            val intent = Intent(this, AccountInfoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initView() {
        setTextWatcher()
        binding.back.setOnClickListener {
            val intent = Intent(this, AccountInfoActivity::class.java)
            finish()
            startActivity(intent)
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
                        binding.changeBtn.background = ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.bg_primary_button
                        )
                        binding.changeBtn.setClickable(true)
                    } else {
                        showShortToast("비밀번호에는의 한개 이상 숫자와 특수문자가 포함되어야합니다.")
                        binding.changeBtn.background = ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.registerbuttondesign
                        )
                        binding.changeBtn.setClickable(false)
                    }
                }
            }
        })
    }
}