package com.semicolon.walkhub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.semicolon.walkhub.viewmodel.UserLoginViewModel

class UserLoginActivity : AppCompatActivity() {

    private val userLoginViewModel: UserLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userLoginViewModel.getLoginData()
    }

    private fun observeEvent() {
        userLoginViewModel.run {
            id.observe(this@UserLoginActivity, {
                TODO("view에 값 넣어주기")
            })
            pw.observe(this@UserLoginActivity, {
                TODO("view에 값 넣어주기")
            })
        }
    }
}