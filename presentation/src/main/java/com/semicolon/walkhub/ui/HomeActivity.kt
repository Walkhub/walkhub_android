package com.semicolon.walkhub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityHomeBinding
import com.semicolon.walkhub.ui.login.LoginActivity
import com.semicolon.walkhub.ui.register.SearchSchoolActivity
import com.semicolon.walkhub.ui.register.ui.Register

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btJoin.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}