package com.semicolon.walkhub.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityScanHealthInformationBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class ScanHealthInformationActivity : BaseActivity<ActivityScanHealthInformationBinding>(
    R.layout.activity_scan_health_information
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btContinue.setOnClickListener {
            finishRegister()
        }

        binding.btMan.setOnClickListener {
            changeGenderMan()
        }

        binding.btWo.setOnClickListener {
            changeGenderWo()
        }
    }

    override fun initView() {

    }

    private fun finishRegister() {
        if(binding.eTcm.length() < 1 || binding.etkg.length() < 1 ) {
            showLongToast("신체 정보를 올바르게 입력해주세요.")
        }
    }

    private fun changeGenderMan() {
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
}