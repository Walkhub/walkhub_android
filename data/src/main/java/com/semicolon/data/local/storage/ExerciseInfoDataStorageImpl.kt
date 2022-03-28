package com.semicolon.data.local.storage

import android.content.Context
import androidx.preference.PreferenceManager
import com.semicolon.domain.entity.exercise.GoalEntity
import com.semicolon.domain.enums.GoalType
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.domain.enums.toMeasureGoalType
import com.semicolon.domain.enums.toMeasuringState
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExerciseInfoDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExerciseInfoDataStorage {

    override fun setFirstStartTime(timeAsSecond: Long) =
        getSharedPreference().edit().let {
            it.putLong(FIRST_START_TIME, timeAsSecond)
            it.apply()
        }

    override fun fetchFirstStartTime(): Long =
        getSharedPreference().getLong(FIRST_START_TIME, -1)

    override fun setStartTime(timeAsSecond: Long) =
        getSharedPreference().edit().let {
            it.putLong(START_TIME, timeAsSecond)
            it.apply()
        }

    override fun fetchStartTime(): Long =
        getSharedPreference().getLong(START_TIME, -1)

    override fun setPausedTime(timeAsSecond: Long) =
        getSharedPreference().edit().let {
            it.putLong(PAUSED_TIME, timeAsSecond)
            it.apply()
        }

    override fun fetchPausedTime(): Long =
        getSharedPreference().getLong(PAUSED_TIME, 0)

    override fun setExerciseId(id: Int) =
        getSharedPreference().edit().let {
            it.putInt(EXERCISE_ID, id)
            it.apply()
        }

    override fun fetchExerciseId(): Int =
        getSharedPreference().getInt(EXERCISE_ID, -1)

    override fun setIsMeasuring(isMeasuring: MeasuringState) =
        getSharedPreference().edit().let {
            it.putInt(IS_MEASURING, isMeasuring.id)
            it.apply()
        }

    override fun isMeasuring(): MeasuringState =
        getSharedPreference()
            .getInt(IS_MEASURING, MeasuringState.FINISHED.id)
            .toMeasuringState()

    override fun setGoal(goalEntity: GoalEntity) =
        getSharedPreference().edit().let {
            it.putInt(GOAL, goalEntity.goal)
            it.putString(GOAL_TYPE, goalEntity.goalType.value)
            it.apply()
        }

    override fun fetchGoal(): GoalEntity {
        val sharedPreferences = getSharedPreference()
        val goal = sharedPreferences.getInt(GOAL, 0)
        val goalType = sharedPreferences.getString(GOAL_TYPE, "")
        return GoalEntity(goal, goalType?.toMeasureGoalType() ?: GoalType.WALK_COUNT)
    }

    private fun getSharedPreference() =
        PreferenceManager.getDefaultSharedPreferences(context)

    companion object Key {
        const val FIRST_START_TIME = "FIRST_START_TIME"
        const val START_TIME = "START_TIME"
        const val EXERCISE_ID = "EXERCISE_ID"
        const val IS_MEASURING = "IS_MEASURING"
        const val PAUSED_TIME = "PAUSED_TIME"
        const val GOAL = "GOAL"
        const val GOAL_TYPE = "GOAL_TYPE"
    }

}