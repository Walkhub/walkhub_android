package com.semicolon.data.local.storage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SpeedDataStorageImpl(
    @ApplicationContext private val context: Context
) : SpeedDataStorage {

    override fun fetchCurrentSpeed(): Flow<Float> {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return callbackFlow {
            if (checkPermission())
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0f
                ) { location -> if (location.hasSpeed()) trySend(location.speed) }
        }
    }

    private fun checkPermission() =
        ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
}