package com.semicolon.walkhub.ui.login

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nms_android_v1.base.BaseActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityLoginBinding
import android.widget.Toast

import com.semicolon.walkhub.ui.MainActivity


class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {

    override fun initView() {
        requestPermissions()
        fitSignIn()
    }

    private fun requestPermissions() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
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
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .addDataType(DataType.AGGREGATE_MOVE_MINUTES)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED)
            .addDataType(DataType.TYPE_LOCATION_SAMPLE)
            .build()
        GoogleSignIn.requestPermissions(
            this, 0,
            GoogleSignIn.getAccountForExtension(this, fitnessOptions)
        )
    }


}