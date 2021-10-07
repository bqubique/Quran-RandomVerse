package com.bqubique.quran_randomayah

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.databinding.ActivityMainBinding
import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import com.bqubique.quran_randomayah.viewmodel.AyahViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var quranApi: QuranApi

    private lateinit var arabicAyah: ArabicAyah
    private lateinit var englishAyah: Ayah
    private lateinit var ayahViewModel: AyahViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        englishAyah = getRandomAyah()
        arabicAyah = getArabicAyah(englishAyah.verse.verseKey)

        ayahViewModel = ViewModelProvider(this).get(AyahViewModel::class.java)
        getVerses()

        binding.refreshFab.setOnClickListener{
            getVerses()
        }
    }

    private fun getVerses() {
        ayahViewModel.refresh()
        ayahViewModel.englishVerse.observe(this, Observer {verse->
            binding.tvEnglish.text = verse.verse.translations[0].text
        })

        ayahViewModel.arabicVerse.observe(this, Observer {verse->
            binding.tvArabic.text = verse.verses[0].textUthmani
            binding.tvVerseKey.text = verse.verses[0].verseKey
        })

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