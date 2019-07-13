package com.example.android.countryinfo.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.android.countryinfo.database.DetailsDAO

class CanadaInfoViewModelFactory(
    private val application: Application) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CanadaInfoViewModel::class.java)) {
            return CanadaInfoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}