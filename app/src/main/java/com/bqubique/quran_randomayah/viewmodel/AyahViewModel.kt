package com.bqubique.quran_randomayah.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.model.ArabicAyah
import com.bqubique.quran_randomayah.model.Ayah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AyahViewModel @Inject constructor(
    private val quranApi: QuranApi
) : ViewModel() {

    var englishVerse = MutableLiveData<Ayah>()
    var arabicVerse = MutableLiveData<ArabicAyah>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()

    fun refresh() {
        loading.value = true
        getVerse()
    }

    private fun getVerse() {
        lateinit var arabicVerseResponse: ArabicAyah
        lateinit var englishVerseResponse: Ayah

        loading.value = true
        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                englishVerseResponse = quranApi.getRandomAyah()
                arabicVerseResponse = quranApi.getArabicAyah(englishVerseResponse.verse.verseKey)
            }.join()
        }

        arabicVerseResponse.let {
            loading.value = false
            error.value = false
            arabicVerse.value = it
        }

        englishVerseResponse.let {
            loading.value = false
            error.value = false
            englishVerse.value = it
        }

    }
}