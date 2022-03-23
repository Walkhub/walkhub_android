package com.semicolon.data.local.storage

import android.content.Context
import androidx.preference.PreferenceManager
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FitnessAccumulateDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FitnessAccumulateDataStorage {

    override fun accumulate(walkRecord: WalkRecordEntity) =
        getSharedPreference().edit().let {
            it.putInt(ACCUMULATED_STEPS, walkRecord.walkCount)
            it.putInt(ACCUMULATED_DISTANCE, walkRecord.traveledDistanceAsMeter)
            it.putFloat(ACCUMULATED_CALORIES, walkRecord.burnedKilocalories)
            it.apply()

        }

    override fun fetchAccumulatedData(): WalkRecordEntity {
        val sharedPreference = getSharedPreference()
        val steps = sharedPreference.getInt(ACCUMULATED_STEPS, 0)
        val distance = sharedPreference.getInt(ACCUMULATED_DISTANCE, 0)
        val calories = sharedPreference.getFloat(ACCUMULATED_CALORIES, 0f)
        return WalkRecordEntity(
            walkCount = steps,
            traveledDistanceAsMeter = distance,
            burnedKilocalories = calories
        )
    }

    override fun clearAccumulatedData() =
        getSharedPreference().edit().let {
            it.remove(ACCUMULATED_STEPS)
            it.remove(ACCUMULATED_DISTANCE)
            it.remove(ACCUMULATED_CALORIES)
            it.apply()
        }

    override fun addPausedTime(timeAsSecond: Long) =
        getSharedPreference().edit().let {
            val pausedTime = fetchPausedTime() + timeAsSecond
            it.putLong(ACCUMULATED_TIME, pausedTime)
            it.apply()
        }

    override fun fetchPausedTime(): Long =
        getSharedPreference().getLong(ACCUMULATED_TIME, 0)

    override fun clearPausedTime() =
        getSharedPreference().edit().let {
            it.remove(ACCUMULATED_TIME)
            it.apply()
        }

    private fun getSharedPreference() =
        PreferenceManager.getDefaultSharedPreferences(context)

    companion object Key {
        const val ACCUMULATED_STEPS = "ACCUMULATED_STEPS"
        const val ACCUMULATED_DISTANCE = "ACCUMULATED_DISTANCE"
        const val ACCUMULATED_CALORIES = "ACCUMULATED_CALORIES"
        const val ACCUMULATED_TIME = "ACCUMULATED_TIME"
    }
}