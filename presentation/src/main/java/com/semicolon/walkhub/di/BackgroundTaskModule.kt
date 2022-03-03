package com.semicolon.walkhub.di

import com.semicolon.data.background.task.ExerciseBackgroundTask
import com.semicolon.data.background.task.ExerciseBackgroundTaskImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BackgroundTaskModule {

    @Binds
    abstract fun provideExerciseBackgroundTask(
        exerciseBackgroundTaskImpl: ExerciseBackgroundTaskImpl
    ): ExerciseBackgroundTask
}