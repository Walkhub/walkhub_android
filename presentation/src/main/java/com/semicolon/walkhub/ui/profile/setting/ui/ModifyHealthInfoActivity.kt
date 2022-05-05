package com.semicolon.walkhub.ui.profile.setting.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.semicolon.domain.entity.users.FetchUserHealthEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityModifyHealthInfoBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyHealthInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModifyHealthInfoActivity : BaseActivity<ActivityModifyHealthInfoBinding>(
    R.layout.activity_modify_health_info
) {
    private val vm: ModifyHealthInfoViewModel by viewModels()
    private var sex = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchUserHealth()


        binding.modifyDone.setOnClickListener {
            val height = binding.editHeight.text.toString().toDouble()
            val weight = binding.editWeight.text.toString().toInt()


            vm.patchUserHealth(height = height, weight = weight, sex = sex)
            val intent = Intent(this, ModifyHealthInfoActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.modifyDone.setClickable(false)
        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

    }

    private fun handleEvent(event: ModifyHealthInfoViewModel.Event) = when (event) {
        is ModifyHealthInfoViewModel.Event.FetchUserHealth -> {
            setHealthInfo(event.fetchUserHealthData)
        }

        is ModifyHealthInfoViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        setTextWatcher()

        binding.editWeight.nextFocusDownId = binding.editWeight.id
        binding.back.setOnClickListener {
            finish()
        }

        binding.manBtn.setOnClickListener {
            sex = "MALE"
            binding.manBtn.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.register_btn
            )
            binding.girlBtn.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.girl_btn
            )
            binding.manBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            binding.girlBtn.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.gray_800
                )
            )
        }

        binding.girlBtn.setOnClickListener {
            sex = "FEMALE"
            binding.girlBtn.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.register_btn
            )
            binding.manBtn.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.girl_btn
            )
            binding.girlBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            binding.manBtn.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.gray_800
                )
            )
        }

    }

    private fun setTextWatcher() {
        binding.editWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.weight.invisible()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.editWeight.text.isEmpty()) {
                    binding.weight.visible()
                } else if (binding.editHeight.length() > 2 && binding.editWeight.length() > 1) {
                    binding.modifyDone.background = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.bg_primary_button
                    )
                    binding.modifyDone.setClickable(true)
                } else if (binding.editHeight.length() < 3 || binding.editWeight.length() < 2) {
                    binding.modifyDone.background = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.registerbuttondesign
                    )
                    binding.modifyDone.setClickable(false)
                }
            }
        })

        binding.editHeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.height.invisible()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.editWeight.text.isEmpty()) {
                    binding.weight.visible()
                } else if (binding.editHeight.length() > 2 && binding.editWeight.length() > 2) {
                    binding.modifyDone.background = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.bg_primary_button
                    )
                    binding.modifyDone.setClickable(true)
                } else if (binding.editHeight.length() < 1 || binding.editWeight.length() < 1) {
                    binding.modifyDone.background = ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.registerbuttondesign
                    )
                    binding.modifyDone.setClickable(false)
                }
            }
        })
    }

    private fun setHealthInfo(fetchUserHealthData: FetchUserHealthEntity) {
        binding.weight.text = fetchUserHealthData.weight.toString()
        binding.height.text = fetchUserHealthData.height.toString()
    }


}