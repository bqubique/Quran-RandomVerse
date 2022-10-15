package com.bqubique.quran_randomayah.di

import com.bqubique.quran_randomayah.api.HadithApi
import com.bqubique.quran_randomayah.api.VerseApi
import com.bqubique.quran_randomayah.util.Constants
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@dagger.Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    fun provideVerseApi(): VerseApi =
        Retrofit
            .Builder()
            .baseUrl(Constants.VERSE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VerseApi::class.java)

    @Provides
    fun provideHadithApi(): HadithApi =
        Retrofit
            .Builder()
            .baseUrl(Constants.HADITH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HadithApi::class.java)

}