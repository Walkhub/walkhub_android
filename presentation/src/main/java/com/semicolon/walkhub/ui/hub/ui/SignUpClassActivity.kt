package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySignUpClassBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.onTextChanged
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.hub.SignUpClassViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpClassActivity : BaseActivity<ActivitySignUpClassBinding>(
    R.layout.activity_sign_up_class
) {
    private val vm: SignUpClassViewModel by viewModels()

    private var page: Int = 1
    private var classCode: String? = null
    private var classNum: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        movePage(1)
    }

    private fun handleEvent(event: SignUpClassViewModel.Event) = when (event) {
        is SignUpClassViewModel.Event.Success -> {
            showShortToast(resources.getString(R.string.success_join_class))
            finish()
        }
        is SignUpClassViewModel.Event.ErrorMessage -> {
            binding.tvError.text = event.message
            showShortToast(event.message)
        }
        is SignUpClassViewModel.Event.ClassCodeState -> {
            classCode(event.code)
        }
    }

    private fun classCode(state: Boolean) {
        if (state) {
            classCode = binding.etClassCode.text.toString()
            binding.tvError.invisible()
            movePage(++page)
        } else {
            binding.tvError.visible()
            binding.tvError.text = resources.getString(R.string.error_join_class)
            binding.etClassCode.text = null
        }
    }

    override fun initView() {
        binding.ivBack.setOnClickListener {
            if (page == 1) {
                finish()
            } else {
                movePage(--page)
            }
        }

        binding.etClassCode.setOnPinEnteredListener { str ->
            if (str.length <= 7) {
                binding.tvNext.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.register_btn
                )

                binding.tvNext.setOnClickListener {
                    classCode = binding.etClassCode.text.toString()
                    binding.tvError.invisible()
                    movePage(++page)
                }
            }
        }

        binding.etNumber.onTextChanged { s, _, _, _ ->
            if (s?.length!! >= 1) {
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

        binding.etTitle.text = resources.getString(R.string.join_class_and_check_rank)
        binding.etLore.text = resources.getString(R.string.input_class_code)

        binding.tvNext.setOnClickListener {
            movePage(++page)
        }
    }

    private fun classNum() {
        binding.tvSecondNext.visible()
        binding.tvNext.invisible()

        binding.etNumber.visible()
        binding.etClassCode.invisible()

        binding.etTitle.text = resources.getString(R.string.registration_number)
        binding.etLore.text = resources.getString(R.string.input_class_number)

        binding.tvSecondNext.setOnClickListener {
            classNum = binding.etNumber.text.toString().toInt()
            movePage(++page)
        }

        binding.tvNext.setOnClickListener {
            classNum = binding.etNumber.text.toString().toInt()
            movePage(++page)
        }
    }

    private fun signUpClass() {
        vm.signUpClass(classCode!!, classNum!!)
    }

    override fun onBackPressed() =
        movePage(--page)

}