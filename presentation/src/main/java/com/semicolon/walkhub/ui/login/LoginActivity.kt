package com.semicolon.walkhub.ui.login

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityLoginBinding
import com.semicolon.walkhub.ui.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(
   R.layout.activity_login
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btLogin.setOnClickListener {
            loginToast()
        }

        binding.btLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginToast() {
        val id = binding.etId.text
        val password = binding.etPassword.text
        if (id.isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        }

        else if (id.isNotEmpty() && password.isEmpty()){
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }

        else if (id.isNotEmpty() && password.isNotEmpty()){

        }
    }

    private val fitnessOptions =
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .addDataType(DataType.AGGREGATE_MOVE_MINUTES)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED)
            .addDataType(DataType.TYPE_LOCATION_SAMPLE)
            .build()

    private fun requestPermissions() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                fitSignIn()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                finish()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            TedPermission.create()
                .setPermissionListener(permissionListener)
                .setPermissions(
                    Manifest.permission.ACTIVITY_RECOGNITION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ).check()
        } else {
            TedPermission.create()
                .setPermissionListener(permissionListener)
                .setPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ).check()
        }
    }

    private fun fitSignIn() {
        GoogleSignIn.requestPermissions(
            this, 0,
            GoogleSignIn.getAccountForExtension(this, fitnessOptions),
            fitnessOptions
        )
    }

    override fun initView() {

    }
}