package com.example.android.countryinfo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.android.countryinfo.model.CanadaInfo
import com.example.android.countryinfo.model.Details
import com.example.android.countryinfo.repository.CanadaInfoRepository

class CanadaInfoViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var repository: CanadaInfoRepository =
        CanadaInfoRepository(application)

    fun getAllDetails(): LiveData<List<Details>> {

        return repository.getAllDetails()

    }

    fun insert() {
        repository.insert()
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

    fun getTitleInfo(): LiveData<List<CanadaInfo>>{
        return repository.getTitleInfo()
    }

}