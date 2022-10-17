package com.bqubique.noble_reminder.di

import com.bqubique.noble_reminder.api.HadithApi
import com.bqubique.noble_reminder.api.VerseApi
import com.bqubique.noble_reminder.util.Constants
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@dagger.Module
@InstallIn(SingletonComponent::class)
class Module {

//    private val loggingInterceptor =
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
//        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideVerseApi(): VerseApi = Retrofit
        .Builder()
        .baseUrl(Constants.VERSE_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(VerseApi::class.java)

    /*
    * OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).build()
                * */

    @Provides
    fun provideHadithApi(): HadithApi = Retrofit
        .Builder()
        .baseUrl(Constants.HADITH_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HadithApi::class.java)

}