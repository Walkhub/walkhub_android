package com.semicolon.walkhub.ui.register.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityAgreeBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.register.ScanHealthInformationActivity

class AgreeActivity : BaseActivity<ActivityAgreeBinding>(
    R.layout.activity_agree
) {
    override fun initView() {
        binding.instructionConstraint.setOnClickListener {
            val intent = Intent(this, ServiceInstructionActivity::class.java)
            startActivity(intent)
        }

        binding.privacyConst.setOnClickListener {
            val intent = Intent(this, PrivacyActivity::class.java)
            startActivity(intent)
        }

        binding.btJoin.setOnClickListener {
            if (binding.cbAll.isChecked){
                val intent = Intent(this, ScanHealthInformationActivity::class.java)
                startActivity(intent)
            } else {
                showShortToast("모든 약관에 동의해주세요.")
            }

        }
    }
}