package com.bqubique.quran_randomayah.api

import com.bqubique.quran_randomayah.model.Ayah
import retrofit2.http.GET

interface QuranApi {
    @GET("api/v4/verses/random?language=en&translations=22#")
    suspend fun getRandomAyah(): Ayah
}