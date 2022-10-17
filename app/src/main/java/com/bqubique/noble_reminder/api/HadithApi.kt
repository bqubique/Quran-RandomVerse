package com.bqubique.noble_reminder.api

import com.bqubique.noble_reminder.model.hadith.HadithModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface HadithApi {

    @GET("v1/hadiths/random")
    @Headers("X-API-Key: SqD712P3E82xnwOAEOkGd5JZH8s9wRR24TqNFzjk")
    suspend fun getRandomHadith() : Response<HadithModel>
}