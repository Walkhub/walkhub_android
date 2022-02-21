package com.semicolon.walkhub.di

import com.semicolon.data.interceptor.AuthorizationInterceptor
import com.semicolon.data.remote.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    private const val BASE_URL = "https://server.walkhub.co.kr"

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
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()

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
    fun provideChallengeApi(retrofit: Retrofit) : ChallengeApi =
        retrofit.create(ChallengeApi::class.java)

    @Provides
    fun provideExerciseApi(retrofit: Retrofit) : ExerciseApi =
        retrofit.create(ExerciseApi::class.java)

    @Provides
    fun provideImageApi(retrofit: Retrofit) : ImagesApi =
        retrofit.create(ImagesApi::class.java)

    @Provides
    fun provideNoticeApi(retrofit: Retrofit) : NoticesApi =
        retrofit.create(NoticesApi::class.java)

    @Provides
    fun provideRankApi(retrofit: Retrofit) : RankApi =
        retrofit.create(RankApi::class.java)

    @Provides
    fun provideSchoolApi(retrofit: Retrofit) : SchoolApi =
        retrofit.create(SchoolApi::class.java)

    @Provides
    fun provideUserApi(retrofit: Retrofit) : UserApi =
        retrofit.create(UserApi::class.java)
}