package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySignUpClassBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.visible

class SignUpClassActivity : BaseActivity<ActivitySignUpClassBinding>(
    R.layout.activity_sign_up_class
) {
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movePage(1)
    }

    override fun initView() {
        binding.ivBack.setOnClickListener {
            if (page == 1) {
                finish()
            } else {
                movePage(--page)
            }
        }

        binding.tvNext.setOnClickListener {
            movePage(++page)
        }

        binding.etClassCode.setOnPinEnteredListener { str ->
            if (str.toString().equals("1234567")) {
                movePage(++page)
            } else {
                Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.etNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! >= 4) {
                    binding.tvNext.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.bg_primary_button)
                } else {
                    binding.tvNext.background =
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.bg_primary_button_off
                        )
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

    }

    private fun movePage(page: Int) {
        when (page) {
            1 -> verifyCode()
            2 -> classNum()
            3 -> signUpClass()
        }
    }

    private fun verifyCode() {
        binding.etNumber.invisible()
        binding.etClassCode.visible()

        binding.etTitle.text = "반 친구들과 함께 걷고\n내 랭킹 확인하기"
        binding.etLore.text = "반 가입코드를 입력해주세요."
    }

    private fun classNum() {
        binding.etNumber.visible()
        binding.etClassCode.invisible()

        binding.etTitle.text = "번호 등록"
        binding.etLore.text = "반에서 사용하는 번호를 입력해주세요."
    }

    private fun signUpClass() {
        finish()
    }
}