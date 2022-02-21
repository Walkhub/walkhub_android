package com.semicolon.data.local.storage

import android.content.Context
import androidx.preference.PreferenceManager
import com.semicolon.data.local.storage.ExerciseInfoDataStorageImpl.Key.EXERCISE_ID
import com.semicolon.data.local.storage.ExerciseInfoDataStorageImpl.Key.IS_MEASURING
import com.semicolon.data.local.storage.ExerciseInfoDataStorageImpl.Key.START_TIME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExerciseInfoDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExerciseInfoDataStorage {

    override fun setStartTime(timeAsSecond: Long) =
        getSharedPreference().edit().let {
            it.putLong(START_TIME, timeAsSecond)
            it.apply()
        }

    override fun fetchStartTime(): Long =
        getSharedPreference().getLong(START_TIME, -1)

    override fun setExerciseId(id: Int) =
        getSharedPreference().edit().let {
            it.putInt(EXERCISE_ID, id)
            it.apply()
        }

    override fun fetchExerciseId(): Int =
        getSharedPreference().getInt(EXERCISE_ID, -1)

    override fun setIsMeasuring(isMeasuring: Boolean) =
        getSharedPreference().edit().let {
            it.putBoolean(IS_MEASURING, isMeasuring)
            it.apply()
        }

    override fun isMeasuring(): Boolean =
        getSharedPreference().getBoolean(IS_MEASURING, false)

    private fun getSharedPreference() =
        PreferenceManager.getDefaultSharedPreferences(context)

    private object Key {
        const val START_TIME = "START_TIME"
        const val EXERCISE_ID = "EXERCISE_ID"
        const val IS_MEASURING = "IS_MEASURING"
    }

}