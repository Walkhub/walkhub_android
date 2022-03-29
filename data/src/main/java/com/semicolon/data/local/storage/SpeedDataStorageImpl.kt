package com.semicolon.data.local.storage

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SpeedDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SpeedDataStorage {

    @SuppressLint("MissingPermission")
    override fun fetchCurrentSpeed(): Flow<Float> {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return callbackFlow {
            if (checkPermission())
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0f
                ) { location -> if (location.hasSpeed()) trySend(location.speed) }
            awaitClose()
        }
    }

    private fun checkPermission() =
        ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
}