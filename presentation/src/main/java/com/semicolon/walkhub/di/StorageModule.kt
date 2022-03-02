package com.semicolon.walkhub.di

import com.semicolon.data.local.storage.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun provideAuthDataStorage(
        authDataStorageImpl: AuthDataStorageImpl
    ): AuthDataStorage

    @Binds
    abstract fun provideExerciseInfoDataStorage(
        exerciseInfoDataStorageImpl: ExerciseInfoDataStorageImpl
    ): ExerciseInfoDataStorage

    @Binds
    abstract fun provideFitnessDataStorage(
        fitnessDataStorageImpl: FitnessDataStorageImpl
    ): FitnessDataStorage

    @Binds
    abstract fun provideFitnessAccumulateDataStorage(
        fitnessAccumulateDataStorageImpl: FitnessAccumulateDataStorageImpl
    ): FitnessAccumulateDataStorage

    @Binds
    abstract fun provideSpeedDataStorage(
        speedDataStorageImpl: SpeedDataStorageImpl
    ): SpeedDataStorage
}