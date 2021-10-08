package com.bqubique.quran_randomayah.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.databinding.ActivityMainBinding
import com.bqubique.quran_randomayah.viewmodel.AyahViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var quranApi: QuranApi

    private lateinit var ayahViewModel: AyahViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ayahViewModel = ViewModelProvider(this).get(AyahViewModel::class.java)
        getVerses()

        binding.refreshFab.setOnClickListener{
            getVerses()
        }
    }

    private fun getVerses() {
        ayahViewModel.refresh()

        ayahViewModel.loading.observe(this, {loading->
            if (loading!!) binding.progressBar.visibility = View.VISIBLE else binding.progressBar.visibility = View.GONE
        })

        ayahViewModel.englishVerse.observe(this, {verse->
            binding.tvEnglish.text = verse.verse.translations[0].text
        })

        ayahViewModel.arabicVerse.observe(this,  {verse->
            binding.tvArabic.text = verse.verses[0].textUthmani
            binding.tvVerseKey.text = verse.verses[0].verseKey
        })
    }
}