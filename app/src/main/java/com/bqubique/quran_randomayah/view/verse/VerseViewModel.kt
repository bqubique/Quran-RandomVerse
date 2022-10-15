package com.bqubique.quran_randomayah.view.verse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bqubique.quran_randomayah.api.VerseApi
import com.bqubique.quran_randomayah.model.arabic_verse.ArabicVerseModel
import com.bqubique.quran_randomayah.model.english_verse.EnglishVerseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerseViewModel @Inject constructor(
    private val verseApi: VerseApi
) : ViewModel() {
    private var _englishVerse = MutableLiveData<EnglishVerseModel>()
    var englishVerse: LiveData<EnglishVerseModel> = _englishVerse

    private var _arabicVerse = MutableLiveData<ArabicVerseModel>()
    var arabicVerse: LiveData<ArabicVerseModel> = _arabicVerse

    private var _loading = MutableLiveData<Boolean>(false)
    var loading: LiveData<Boolean> = _loading

    init {
        getVerse()
    }

    fun getVerse() {
        _loading.value = true
        viewModelScope.launch {
            _englishVerse.value = verseApi.getRandomEnglishVerse().body()
            _arabicVerse.value =
                verseApi.getArabicVerseByVerseKey(verseKey = _englishVerse.value!!.verse.verseKey)
                    .body()
            _loading.value = false
        }
    }
}