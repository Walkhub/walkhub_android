package com.semicolon.data.background.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.location.LocationManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

@HiltWorker
class LocationTrackingWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val localExerciseDataSource: LocalExerciseDataSource
) : CoroutineWorker(context, params) {

    private val mContext = context

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as
                LocationManager

    override suspend fun doWork(): Result {
        setForeground(createForegroundInfo())
        recordLocation()
        return Result.success()
    }

    private suspend fun recordLocation() = suspendCoroutine<Unit> {
        if (checkPermission())
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0F
            ) {
                val location = LocationRecordEntity(
                    latitude = it.latitude,
                    longitude = it.longitude
                )
                CoroutineScope(Dispatchers.IO).launch {
                    localExerciseDataSource.addLocationRecord(location)
                }
            }
    }

    private fun checkPermission() =
        ActivityCompat.checkSelfPermission(
            mContext, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            mContext, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun createForegroundInfo(): ForegroundInfo {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID, TITLE, NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notification = NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setContentTitle(TITLE)
            .setContentText(MESSAGE)
            .setOngoing(true)
            .build()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            ForegroundInfo(NOTIFICATION_ID, notification, FOREGROUND_SERVICE_TYPE_LOCATION)
        else ForegroundInfo(NOTIFICATION_ID, notification)
    }

    companion object {
        const val NOTIFICATION_ID = 123
        const val CHANNEL_ID = "WALKHUB_MEASURING"
        const val TITLE = "Walkhub"
        const val MESSAGE = "운동 기록을 측정중입니다"
    }
}