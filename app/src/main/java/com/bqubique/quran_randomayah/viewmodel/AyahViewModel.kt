package com.bqubique.quran_randomayah.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bqubique.quran_randomayah.api.HadithApi
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.model.hadith.Hadith
import com.bqubique.quran_randomayah.model.verse.ArabicAyah
import com.bqubique.quran_randomayah.model.verse.Ayah
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "AyahViewModel"

@HiltViewModel
class AyahViewModel @Inject constructor(
    private val quranApi: QuranApi,
    private val hadithApi: HadithApi
) : ViewModel() {

    private var _englishVerse = MutableLiveData<Ayah>()
    var englishVerse: LiveData<Ayah> = _englishVerse
    private var _arabicVerse = MutableLiveData<ArabicAyah>()
    var arabicVerse: LiveData<ArabicAyah> = _arabicVerse

    private var _hadith = MutableLiveData<Hadith>()
    var hadith: LiveData<Hadith> = _hadith

    private var _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    init {
        getVerse()
    }

    fun getVerse() {
        _loading.value = true
        viewModelScope.launch {
            _englishVerse.value = quranApi.getRandomAyah().body()
            _arabicVerse.value =
                quranApi.getArabicAyah(_englishVerse.value!!.verse.verseKey).body()
//            do {
                _hadith.value = hadithApi.getRandomHadith().body()
//            }
//            while (_hadith.value!!.hadith[0].body.length > 200)
            delay(1500L)
            _loading.value = false
        }
    }
}