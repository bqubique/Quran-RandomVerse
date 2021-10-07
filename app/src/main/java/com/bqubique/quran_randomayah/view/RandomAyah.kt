package com.bqubique.quran_randomayah.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.databinding.FragmentRandomAyahBinding

import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class RandomAyah : Fragment() {

    @Inject
    lateinit var quranApi: QuranApi

    lateinit var binding: FragmentRandomAyahBinding

    lateinit var arabicAyah: ArabicAyah
    lateinit var englishAyah: Ayah

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomAyahBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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