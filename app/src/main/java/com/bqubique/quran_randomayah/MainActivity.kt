package com.bqubique.quran_randomayah

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.databinding.ActivityMainBinding
import com.bqubique.quran_randomayah.model.Ayah
import com.bqubique.quran_randomayah.service.QuranService
import kotlinx.coroutines.*

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            getRandomAyah()
        }
    }

    private suspend fun getRandomAyah() {
        lateinit var randomAyah: Ayah
        val wait = GlobalScope.launch {
            randomAyah = QuranService().retrofitInstance.getRandomAyah()
            Log.d("MAINACT", "SUSPEND FINISHED")
        }
        wait.join()
        Log.d("MAINACT", randomAyah.verse.translations[0].text)
    }
}