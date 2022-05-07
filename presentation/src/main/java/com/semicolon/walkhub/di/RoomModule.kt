package com.semicolon.walkhub.di

import android.content.Context
import androidx.room.Room
import com.semicolon.data.local.converter.*
import com.semicolon.data.local.dao.*
import com.semicolon.data.local.database.WalkHubDataBase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideWalkHubDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): WalkHubDataBase = Room
        .databaseBuilder(context, WalkHubDataBase::class.java, "WalkHubDataBase")
        .addTypeConverter(CaloriesListTypeConverter(moshi))
        .addTypeConverter(MyBadgeListTypeConverter(moshi))
        .addTypeConverter(NewBadgeListTypeConverter(moshi))
        .addTypeConverter(NoticeListTypeConverter(moshi))
        .addTypeConverter(UserBadgeListTypeConverter(moshi))
        .addTypeConverter(RankOurSchoolTypeConverter(moshi))
        .addTypeConverter(RankSchoolRankAndSearchTypeConverter(moshi))
        .addTypeConverter(RankSearchUserTypeConverter(moshi))
        .addTypeConverter(RankUserRankTypeConverter(moshi))
        .addTypeConverter(ChallengeParticipantTypeConverter(moshi))
        .addTypeConverter(NotificationTypeConverter(moshi))
        .build()

    @Provides
    fun provideChallengeDao(
        walkHubDataBase: WalkHubDataBase
    ): ChallengeDao = walkHubDataBase.challengeDao()

    @Provides
    fun provideUserDao(
        walkHubDataBase: WalkHubDataBase
    ): UserDao = walkHubDataBase.userDao()

    @Provides
    fun provideNoticeDao(
        walkHubDataBase: WalkHubDataBase
    ): NoticeDao = walkHubDataBase.noticeDao()

    @Provides
    fun provideBadgeDao(
        walkHubDataBase: WalkHubDataBase
    ): BadgeDao = walkHubDataBase.badgeDao()

    @Provides
    fun provideRankDao(
        walkHubDataBase: WalkHubDataBase
    ): RankDao = walkHubDataBase.rankDao()

    @Provides
    fun provideNotificationDao(
        walkHubDataBase: WalkHubDataBase
    ): NotificationDao = walkHubDataBase.notificationDao()

    @Provides
    fun provideLocationRecordDao(
        walkHubDataBase: WalkHubDataBase
    ): LocationRecordDao = walkHubDataBase.locationRecordDao()

    @Provides
    fun provideLevelDao(
        walkHubDataBase: WalkHubDataBase
    ): LevelDao = walkHubDataBase.levelDao()

    @Provides
    fun provideSchoolDao(
        walkHubDataBase: WalkHubDataBase
    ): SchoolDao = walkHubDataBase.schoolDao()
}