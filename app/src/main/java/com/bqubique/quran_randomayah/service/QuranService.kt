package com.bqubique.quran_randomayah.service

import com.bqubique.quran_randomayah.api.QuranApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuranService {

    val retrofitInstance: QuranApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.quran.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuranApi::class.java)
    }
}