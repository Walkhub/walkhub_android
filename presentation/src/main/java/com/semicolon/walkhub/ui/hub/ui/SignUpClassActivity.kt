package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySignUpClassBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.onTextChanged
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.hub.SignUpClassViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.annotations.NotNull

@AndroidEntryPoint
class SignUpClassActivity : BaseActivity<ActivitySignUpClassBinding>(
    R.layout.activity_sign_up_class
) {

    private val vm: SignUpClassViewModel by viewModels()

    private var page: Int = 1

    private var classCode: String ?= null
    private var number: Int ?= null

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
            if (str.length <= 7) {
                if (str.toString() == "1234567") {
                    classCode = str.toString()
                    movePage(++page)
                    binding.tvError.invisible()
                } else {
                    binding.tvError.visible()
                    binding.tvError.text = "반을 찾을 수 없어요. 가입코드를 다시 확인해주세요."
                    binding.etClassCode.text = null
                }
            }
        }

        binding.etNumber.onTextChanged { s, start, before, count ->
            if (s?.length!! >= 4) {
                binding.tvNext.background =
                    ContextCompat.getDrawable(applicationContext, R.drawable.bg_primary_button)
                binding.tvSecondNext.background =
                    ContextCompat.getDrawable(applicationContext, R.color.primary_400)
            } else {
                binding.tvNext.background =
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.bg_primary_button_off
                    )
                binding.tvSecondNext.background =
                    ContextCompat.getDrawable(applicationContext, R.color.gray_300)
            }
        }
    }

    private fun movePage(page: Int) {
        when (page) {
            0 -> finish()
            1 -> verifyCode()
            2 -> classNum()
            3 -> signUpClass()
        }
    }

    private fun verifyCode() {
        binding.tvSecondNext.invisible()
        binding.tvNext.visible()

        binding.etNumber.invisible()
        binding.etClassCode.visible()

        binding.etTitle.text = "반 친구들과 함께 걷고\n내 랭킹 확인하기"
        binding.etLore.text = "반 가입코드를 입력해주세요."
    }

    private fun classNum() {
        binding.tvSecondNext.visible()
        binding.tvNext.invisible()

        binding.etNumber.visible()
        binding.etClassCode.invisible()

        binding.etTitle.text = "번호 등록"
        binding.etLore.text = "반에서 사용하는 번호를 입력해주세요."

        binding.tvSecondNext.setOnClickListener {
            number = binding.etNumber.text.toString().toInt()
            movePage(++page)
        }
    }

    private fun signUpClass() {
        vm.signUpClass(classCode!!, number!!)
    }

    override fun onBackPressed() =
        movePage(--page)

}