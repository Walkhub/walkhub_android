package com.semicolon.walkhub.di

import android.content.Context
import androidx.room.Room
import com.semicolon.data.local.converter.*
import com.semicolon.data.local.dao.BadgeDao
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.dao.NoticeDao
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.database.WalkHubDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideGithubDatabase(
        @ApplicationContext context: Context
    ): WalkHubDataBase = Room
        .databaseBuilder(context, WalkHubDataBase::class.java, "github")
        .addTypeConverter(CaloriesListTypeConverter::class)
        .addTypeConverter(MyBadgeListTypeConverter::class)
        .addTypeConverter(NewBadgeListTypeConverter::class)
        .addTypeConverter(NoticeListTypeConverter::class)
        .addTypeConverter(UserBadgeListTypeConverter::class)
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
}