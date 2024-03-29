package com.bqubique.noble_reminder.api

import com.bqubique.noble_reminder.model.arabic_verse.ArabicVerseModel
import com.bqubique.noble_reminder.model.english_verse.EnglishVerseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VerseApi {

    @GET("api/v4/verses/random?language=en&translations=20#")
    suspend fun getRandomEnglishVerse(): Response<EnglishVerseModel>

    @GET("api/v4/quran/verses/uthmani")
    suspend fun getArabicVerseByVerseKey(
        @Query("verse_key") verseKey: String
    ): Response<ArabicVerseModel>
}