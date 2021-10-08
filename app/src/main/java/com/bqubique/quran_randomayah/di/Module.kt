package com.bqubique.quran_randomayah.di

import com.bqubique.quran_randomayah.api.BackgroundApi
import com.bqubique.quran_randomayah.api.QuranApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    private const val QURAN_BASE_URL = "https://api.quran.com"
    private const val IMG_BASE_URL = "https://api.unsplash.com"

    @Provides
    @Singleton
    fun provideQuranApi(): QuranApi {
        return Retrofit.Builder()
            .baseUrl(QURAN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuranApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBackgroundImageApi() : BackgroundApi{
        return Retrofit.Builder()
            .baseUrl(IMG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BackgroundApi::class.java)
    }
}