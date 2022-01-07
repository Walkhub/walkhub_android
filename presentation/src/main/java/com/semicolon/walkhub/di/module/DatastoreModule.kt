package com.semicolon.walkhub.di.module

import android.content.Context
import com.semicolon.walkhub.util.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatastoreModule {

    @Singleton
    @Provides
    fun datastoreModule(
        @ApplicationContext context: Context
    ) : DataStoreManager = DataStoreManager(context)

}