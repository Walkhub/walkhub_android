package com.semicolon.walkhub.ui.login

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.example.nms_android_v1.base.BaseActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityLoginBinding
import com.semicolon.data.local.datasource.LocalExerciseDataSourceImpl
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.data.local.storage.ExerciseInfoDataStorageImpl
import com.semicolon.data.local.storage.FitnessDataStorageImpl
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

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

    // TODO: 나중에 지워
    private val liveData = MutableLiveData<DailyExerciseEntity>()

    override fun initView() {
        requestPermissions()

        // TODO: 나중에 지워
        liveData.observe(this) {
            binding.steps.setText("걸음 수 : ${it.stepCount.toString()}회")
            binding.times.setText("운동 시간 : ${it.exerciseTimeAsMilli.toString()}ms")
            binding.distance.setText("이동 거리 : ${it.traveledDistanceAsMeter.toString()}m")
            binding.calories.setText("소모한 칼로리 : ${it.burnedKilocalories.toString()}cals")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // TODO: 이 이래는 테스트하려고 임시로 써놓은 코드임
        if (requestCode != 0 && resultCode != RESULT_OK) return
        val a = LocalExerciseDataSourceImpl(
            FitnessDataStorageImpl(this),
            ExerciseInfoDataStorageImpl(this)
        )
        val period = PeriodParam(
            LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond(),
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
        )

        GlobalScope.launch {
            a.startRecordExercise()
        }

        GlobalScope.launch {
            a.fetchDailyExerciseRecordAsFlow().collect {
                liveData.postValue(it)
                println(it.toString())
            }
        }

        GlobalScope.launch {
            println(a.fetchWalkRecord(period).toString() + " walk record with period")
        }

        GlobalScope.launch {
            println(a.fetchLocationRecord(period).toString()+ " location record with period")
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