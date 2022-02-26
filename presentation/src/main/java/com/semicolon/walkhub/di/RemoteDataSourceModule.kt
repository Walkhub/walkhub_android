package com.semicolon.walkhub.di

import com.semicolon.data.remote.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun provideRemoteSocketDataSource(
        remoteSocketDataSourceImpl: RemoteSocketDataSourceImpl,
    ): RemoteSocketDataSource

    @Binds
    abstract fun provideRemoteBadgeDataSource(
        remoteBadgeDataSourceImpl: RemoteBadgeDataSourceImpl
    ): RemoteBadgeDataSource

    @Binds
    abstract fun provideRemoteChallengeDataSource(
        remoteChallengeDataSourceImpl: RemoteChallengeDateSourceImpl
    ): RemoteChallengeDateSource

    @Binds
    abstract fun provideRemoteExerciseDataSource(
        remoteExerciseDataSourceImpl: RemoteExerciseDataSourceImpl
    ): RemoteExerciseDataSource

    @Binds
    abstract fun provideRemoteImagesDataSource(
        remoteImagesDataSourceImpl: RemoteImagesDataSourceImpl
    ): RemoteImagesDataSource

    @Binds
    abstract fun provideRemoteNoticeDataSource(
        remoteNoticeDataSourceImpl: RemoteNoticeDataSourceImpl
    ): RemoteNoticeDataSource

    @Binds
    abstract fun provideRemoteNotificationDataSource(
        remoteNotificationDataSourceImpl: RemoteNotificationDataSourceImpl
    ): RemoteNotificationDataSource

    @Binds
    abstract fun provideRemoteRankDataSource(
        remoteRankDataSourceImpl: RemoteRankDataSourceImpl
    ): RemoteRankDataSource

    @Binds
    abstract fun provideRemoteSchoolDataSource(
        remoteSchoolDataSourceImpl: RemoteSchoolDataSourceImpl
    ): RemoteSchoolDataSource

    @Binds
    abstract fun provideRemoteUserDataSource(
        remoteUserDataSourceImpl: RemoteUserDataSourceImpl
    ): RemoteUserDataSource

    @Binds
    abstract fun provideRemoteLevelDataSource(
        remoteLevelDataSourceImpl: RemoteLevelDataSourceImpl
    ): RemoteLevelDataSource

}