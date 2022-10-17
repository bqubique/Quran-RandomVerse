package com.bqubique.quran_randomayah.view.hadith

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bqubique.quran_randomayah.api.HadithApi
import com.bqubique.quran_randomayah.model.hadith.HadithModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadithViewModel @Inject constructor(
    private val hadithApi: HadithApi
) : ViewModel() {
    private val _hadith = MutableLiveData<HadithModel>()
    val hadith: LiveData<HadithModel> = _hadith

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    init {
        getHadith()
    }

    fun getHadith() {
        _loading.value = true
        viewModelScope.launch {
            _hadith.value = hadithApi.getRandomHadith().body()
            _loading.value = false
        }
    }
}