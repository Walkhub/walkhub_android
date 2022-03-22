package com.semicolon.walkhub.util

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Process
import com.semicolon.domain.exception.user.NeedLoginException
import com.semicolon.walkhub.ui.HomeActivity
import kotlin.system.exitProcess

class WalkhubExceptionHandler(
    application: Application,
    private val crashlyticsExceptionHandler: Thread.UncaughtExceptionHandler
) : Thread.UncaughtExceptionHandler {

    private var lastActivity: Activity? = null
    private var activityCount = 0

    init {
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {

                override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                    lastActivity = p0
                }

                override fun onActivityStarted(p0: Activity) {
                    activityCount++
                    lastActivity = p0
                }

                override fun onActivityStopped(p0: Activity) {
                    activityCount--
                    if (activityCount < 0) lastActivity = null
                }

                override fun onActivityResumed(p0: Activity) {}

                override fun onActivityPaused(p0: Activity) {}

                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

                override fun onActivityDestroyed(p0: Activity) {}
            }
        )
    }

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        if (p1 is NeedLoginException) {
            lastActivity?.run {
                startHomeActivity(this)
            }
        }
        crashlyticsExceptionHandler.uncaughtException(p0, p1)
        Process.killProcess(Process.myPid())
        exitProcess(-1)
    }

    private fun startHomeActivity(activity: Activity) = activity.run {
        val intent = Intent(this, HomeActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}