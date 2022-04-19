package com.semicolon.walkhub.ui.register.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityAgreeBinding
import com.semicolon.walkhub.ui.register.ScanHealthInformationActivity

class AgreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_agree)

        binding.tvInstruction.setOnClickListener {
            val intent = Intent(this, ServiceInstructionActivity::class.java)
            startActivity(intent)
        }

        binding.tvPrivacy.setOnClickListener {
            val intent = Intent(this, PrivacyActivity::class.java)
            startActivity(intent)
        }

        binding.btJoin.setOnClickListener {
            val intent = Intent(this, ScanHealthInformationActivity::class.java)
            startActivity(intent)
        }
    }
}