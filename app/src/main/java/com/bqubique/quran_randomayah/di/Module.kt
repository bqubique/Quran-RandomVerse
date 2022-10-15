package com.bqubique.quran_randomayah.di

import com.bqubique.quran_randomayah.api.Api
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
    fun provideApi(): Api =
        Retrofit
            .Builder()
            .baseUrl(Constants.QURAN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

}