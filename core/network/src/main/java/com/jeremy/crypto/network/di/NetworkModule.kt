package com.jeremy.crypto.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jeremy.crypto.network.service.UpbitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideSerializationConverter(): Converter.Factory {
        return Json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        serializationConverter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.upbit.com")
            .client(okHttpClient)
            .addConverterFactory(serializationConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideUpbitService(retrofit: Retrofit): UpbitService = retrofit.create(
        UpbitService::class.java
    )
}
