package com.semicolon.walkhub.ui.login

import android.Manifest
import android.content.Intent
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
import com.jakewharton.threetenabp.AndroidThreeTen
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.local.datasource.LocalExerciseDataSourceImpl
import com.semicolon.data.local.storage.ExerciseInfoDataStorage
import com.semicolon.data.local.storage.ExerciseInfoDataStorageImpl
import com.semicolon.data.local.storage.FitnessDataStorageImpl

import com.semicolon.walkhub.ui.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {
    private val fitnessOptions =
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .addDataType(DataType.AGGREGATE_MOVE_MINUTES)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED)
            .addDataType(DataType.TYPE_LOCATION_SAMPLE)
            .build()

    override fun initView() {
        requestPermissions()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // TODO: 이 이래는 테스트하려고 임시로 써놓은 코드임
        if (requestCode != 0 && resultCode != RESULT_OK) return
        val a = LocalExerciseDataSourceImpl(
            FitnessDataStorageImpl(this),
            ExerciseInfoDataStorageImpl(this)
        )
        GlobalScope.launch {
            a.fetchDailyExerciseRecordAsFlow().collect {
                println(it.toString())
            }
        }
        // TODO: 나중에 로그인 개발할떄 지워줘
    }

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
}