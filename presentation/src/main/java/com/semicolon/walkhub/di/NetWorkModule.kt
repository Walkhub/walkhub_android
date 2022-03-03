package com.semicolon.walkhub.di

import com.semicolon.data.interceptor.AuthorizationInterceptor
import com.semicolon.data.remote.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    private const val BASE_URL = "https://server.walkhub.co.kr"
    private const val SOCKET_BASE_URL = "http://211.38.86.92:8081/socket.io"

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()

    @Provides
    fun provideOptions(
        okHttpClient: OkHttpClient
    ): IO.Options {
        val options = IO.Options()
        options.callFactory = okHttpClient
        options.webSocketFactory = okHttpClient
        options.transports = arrayOf(WebSocket.NAME)
        return options
    }

    @Provides
    fun provideSocket(
        options: IO.Options
    ): Socket{
        return IO.socket(SOCKET_BASE_URL, options)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideChallengeApi(retrofit: Retrofit): ChallengeApi =
        retrofit.create(ChallengeApi::class.java)

    @Provides
    fun provideExerciseApi(retrofit: Retrofit): ExerciseApi =
        retrofit.create(ExerciseApi::class.java)

    @Provides
    fun provideImageApi(retrofit: Retrofit): ImagesApi =
        retrofit.create(ImagesApi::class.java)

    @Provides
    fun provideNoticeApi(retrofit: Retrofit): NoticesApi =
        retrofit.create(NoticesApi::class.java)

    @Provides
    fun provideNotificationApi(retrofit: Retrofit): NotificationApi =
        retrofit.create(NotificationApi::class.java)

    @Provides
    fun provideRankApi(retrofit: Retrofit): RankApi =
        retrofit.create(RankApi::class.java)

    @Provides
    fun provideSchoolApi(retrofit: Retrofit): SchoolApi =
        retrofit.create(SchoolApi::class.java)

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    fun provideLevelApi(retrofit: Retrofit): LevelApi =
        retrofit.create(LevelApi::class.java)
}