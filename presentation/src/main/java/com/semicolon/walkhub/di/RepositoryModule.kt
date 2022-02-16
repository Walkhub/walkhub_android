package com.semicolon.walkhub.di

import com.semicolon.data.repository.*
import com.semicolon.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesBadgeRepository(
        badgeRepositoryImpl: BadgeRepositoryImpl
    ): BadgeRepository

    @Binds
    abstract fun provideChallengeRepository(
        challengeRepositoryImpl: ChallengeRepository
    ): ChallengeRepository

    @Binds
    abstract fun provideExerciseRepository(
        exerciseRepositoryImpl: ExerciseRepositoryImpl
    ): ExerciseRepository

    @Binds
    abstract fun provideNoticeRepository(
        noticeRepositoryImpl: NoticeRepositoryImpl
    ): NoticeRepository

    @Binds
    abstract fun provideNotificationRepository(
        notificationRepositoryImpl: NotificationRepositoryImpl
    ): NotificationRepository

    @Binds
    abstract fun provideRankRepository(
        rankRepositoryImpl: RankRepositoryImpl
    ): RankRepository

    @Binds
    abstract fun provideSchoolRepository(
        schoolRepositoryImpl: SchoolRepositoryImpl
    ): SchoolRepository

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

}