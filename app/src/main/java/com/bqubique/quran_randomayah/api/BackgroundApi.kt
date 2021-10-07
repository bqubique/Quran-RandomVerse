package com.bqubique.quran_randomayah.api

import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Background
import retrofit2.http.GET
import retrofit2.http.Query

interface BackgroundApi {

    @GET("")
    suspend fun getRandomNaturePicture(): Background

}