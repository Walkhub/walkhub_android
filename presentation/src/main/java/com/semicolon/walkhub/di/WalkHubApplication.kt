package com.semicolon.walkhub.di

import android.app.Application
import androidx.work.Configuration
import com.jakewharton.threetenabp.AndroidThreeTen
import com.semicolon.data.background.worker.CustomWorkerFactory
import com.semicolon.walkhub.util.WalkhubExceptionHandler
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WalkHubApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CustomWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        setCrashHandler()
    }

    private fun setCrashHandler() {
        val crashlyticsExceptionHandler = Thread.getDefaultUncaughtExceptionHandler() ?: return
        Thread.setDefaultUncaughtExceptionHandler(
            WalkhubExceptionHandler(
                this,
                crashlyticsExceptionHandler
            )
        )
    }
}