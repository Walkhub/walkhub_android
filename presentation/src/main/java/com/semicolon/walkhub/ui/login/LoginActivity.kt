package com.semicolon.walkhub.ui.login

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityLoginBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.HomeActivity
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.home.HomeViewModel
import com.semicolon.walkhub.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
   R.layout.activity_login
) {

    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btLogin.setOnClickListener {
            loginToast()
        }

        binding.btLogin.setOnClickListener {
            val accountId: String = binding.etId.text.toString()
            val password: String = binding.etPassword.text.toString()

            vm.postLogin(accountId, password)
        }

        binding.ibBack.setOnClickListener {
            finish()
        }
        
        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: LoginViewModel.Event) = when (event) {
        is LoginViewModel.Event.LoginSuccess-> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        is LoginViewModel.Event.NoInternet -> showShortToast("????????? ????????? ??????????????????.")

        is LoginViewModel.Event.Unauthorized -> showShortToast("????????? ???????????????. ?????? ????????? ????????????.")

        is LoginViewModel.Event.BadRequest -> showShortToast("????????? ???????????????. ????????? ?????? ??????????????????.")

        is LoginViewModel.Event.WrongAccount -> showShortToast("???????????? ??????????????? ????????????.")

        else -> showShortToast("??? ??? ?????? ????????? ?????????????????????.")
    }

    private fun loginToast() {
        val id = binding.etId.text
        val password = binding.etPassword.text
        if (id.isEmpty()) {
            Toast.makeText(this, "???????????? ??????????????????", Toast.LENGTH_SHORT).show()
        }

        else if (id.isNotEmpty() && password.isEmpty()){
            Toast.makeText(this, "??????????????? ??????????????????.", Toast.LENGTH_SHORT).show()
        }

        else if (id.isNotEmpty() && password.isNotEmpty()){

        }
    }

    override fun initView() {

    }
}