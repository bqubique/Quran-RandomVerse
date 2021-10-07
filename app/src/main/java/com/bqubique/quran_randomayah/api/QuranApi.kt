package com.bqubique.quran_randomayah.api

import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import retrofit2.http.GET
import retrofit2.http.Query

interface QuranApi {
    @GET("api/v4/verses/random?language=en&translations=22#")
    suspend fun getRandomAyah(): Ayah

    @GET("api/v4/quran/verses/uthmani")
    suspend fun getArabicAyah(
        @Query("verse_key")
        verseKey: String
    ): ArabicAyah

}