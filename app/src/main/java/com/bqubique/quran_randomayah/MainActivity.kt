package com.bqubique.quran_randomayah

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.databinding.ActivityMainBinding
import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity  : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding


    @Inject
    lateinit var quranApi: QuranApi

    private lateinit var arabicAyah: ArabicAyah
    private lateinit var englishAyah: Ayah

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        englishAyah = getRandomAyah()
        arabicAyah = getArabicAyah(englishAyah.verse.verseKey)

        binding.tvVerseKey.text = arabicAyah.verses[0].verseKey
        binding.tvArabic.text = arabicAyah.verses[0].textUthmani
        binding.tvEnglish.text = englishAyah.verse.translations[0].text

    }

    private fun getRandomAyah(): Ayah {
        lateinit var randomAyah: Ayah
        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                randomAyah = quranApi.getRandomAyah()
                Log.d("MAIN ACT", randomAyah.verse.verseKey)
            }.join()
        }


        return randomAyah
    }

    private fun getArabicAyah(verseKey: String): ArabicAyah {
        lateinit var arabicAyah: ArabicAyah

        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                arabicAyah = quranApi.getArabicAyah(verseKey)
            }.join()
        }
        return arabicAyah
    }
}