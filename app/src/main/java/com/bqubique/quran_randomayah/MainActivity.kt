package com.bqubique.quran_randomayah

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bqubique.QuranApplication
import com.bqubique.quran_randomayah.api.QuranApi
import com.bqubique.quran_randomayah.databinding.ActivityMainBinding
import com.bqubique.quran_randomayah.model.Ayah
import com.bqubique.quran_randomayah.service.QuranService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EarlyEntryPoint
import dagger.hilt.android.internal.migration.InjectedByHilt
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

    }


}