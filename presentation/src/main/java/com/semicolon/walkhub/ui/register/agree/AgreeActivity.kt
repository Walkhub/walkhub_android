package com.semicolon.walkhub.ui.register.agree

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityAgreeBinding

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
    }
}