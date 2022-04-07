package com.semicolon.walkhub.di

import com.semicolon.data.local.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun provideLocalBadgeDataSource(
        localBadgeDataSourceImpl: LocalBadgeDataSourceImpl
    ): LocalBadgeDataSource

    @Binds
    abstract fun provideLocalChallengeDataSource(
        localChallengeDataSourceImpl: LocalChallengeDataSourceImpl
    ): LocalChallengeDataSource

    @Binds
    abstract fun provideLocalSchoolDataSource(
        localSchoolDataSourceImpl: LocalSchoolDataSourceImpl
    ): LocalSchoolDataSource

    @Binds
    abstract fun provideLocalExerciseDataSource(
        localExerciseDataSourceImpl: LocalExerciseDataSourceImpl
    ): LocalExerciseDataSource

    @Binds
    abstract fun provideLocalNoticeDataSource(
        localNoticeDataSourceImpl: LocalNoticeDataSourceImpl
    ): LocalNoticeDataSource

    @Binds
    abstract fun provideNotificationDataSource(
        localNotificationDataSourceImpl: LocalNotificationDataSourceImpl
    ): LocalNotificationDataSource

    @Binds
    abstract fun provideRankDataSource(
        localRankDataSourceImpl: LocalRankDataSourceImpl
    ): LocalRankDataSource

    @Binds
    abstract fun provideLocalUserDataSource(
        localUserDataSourceImpl: LocalUserDataSourceImpl
    ): LocalUserDataSource

    @Binds
    abstract fun provideLocalLevelDataSource(
        localLevelDataSourceImpl: LocalLevelDataSourceImpl
    ): LocalLevelDataSource

}