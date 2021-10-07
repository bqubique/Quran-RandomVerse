package com.bqubique.quran_randomayah.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bqubique.quran_randomayah.R
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.databinding.FragmentRandomAyahBinding
import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RandomAyah : Fragment() {

    @Inject
    lateinit var quranApi: QuranApi

    lateinit var binding: FragmentRandomAyahBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomAyahBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            getRandomAyah()
        }
    }
    private suspend fun getRandomAyah() {
        lateinit var randomAyah: Ayah
        lateinit var s: ArabicAyah
        val wait = GlobalScope.launch {
            randomAyah = quranApi.getRandomAyah()
            s = quranApi.getArabicAyah(randomAyah.verse.verseKey)
        }
        wait.join()
        Log.d("MAINACT", randomAyah.verse.translations[0].text)
        Log.d("MAINACT", s.verses[0].textUthmani)
    }
}