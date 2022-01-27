package com.bqubique.quran_randomayah.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
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

const val TAG = "AyahViewModel"

@HiltViewModel
class AyahViewModel @Inject constructor(
    private val quranApi: QuranApi
) : ViewModel() {

    private var _englishVerse = MutableLiveData<Ayah>()
    var englishVerse: LiveData<Ayah> = _englishVerse
    private var _arabicVerse = MutableLiveData<ArabicAyah>()
    var arabicVerse: LiveData<ArabicAyah> = _arabicVerse

    private var _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    var error = MutableLiveData<Boolean>()

    init {
        getVerse()
    }

    fun getVerse() {
        lateinit var arabicVerseResponse: ArabicAyah
        lateinit var englishVerseResponse: Ayah

        _loading.value = true

        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                englishVerseResponse = quranApi.getRandomAyah()
                arabicVerseResponse = quranApi.getArabicAyah(englishVerseResponse.verse.verseKey)
            }.join()

            _loading.value = false
        }

        arabicVerseResponse.let {
            this._loading.value = false
            error.value = false
            _arabicVerse.value = it
        }

        englishVerseResponse.let {
            this._loading.value = false
            error.value = false
            _englishVerse.value = it
        }
    }
}