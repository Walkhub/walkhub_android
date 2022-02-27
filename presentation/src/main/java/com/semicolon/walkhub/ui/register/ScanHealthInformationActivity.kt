package com.semicolon.walkhub.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    override fun initView() {

    }

    private fun notFinishRegister() {
        if(binding.eTcm.length() < 1 || binding.etkg.length() < 1 ) {
            showLongToast("신체 정보를 올바르게 입력해주세요.")
        }

        else {
            finishRegister()
        }
    }

    private fun finishRegister(){
        when(a) {
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
        a = true
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

        a = true
    }
}