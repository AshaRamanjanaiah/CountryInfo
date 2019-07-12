package com.example.android.countryinfo.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.android.countryinfo.database.CanadaInfoDao
import com.example.android.countryinfo.database.AppDatabase
import com.example.android.countryinfo.database.DetailsDAO
import com.example.android.countryinfo.model.CanadaInfo
import com.example.android.countryinfo.network.CanadaApi
import retrofit2.Call
import retrofit2.Response

class CanadaInfoRepository(application: Application) {

    private var canadaInfoDao: CanadaInfoDao
    private var detailsDao: DetailsDAO

    private val TAG: String = this::class.java.simpleName

    private var allDetails: LiveData<List<CanadaInfo>>

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)
        canadaInfoDao = database.canadaInfoDao
        detailsDao = database.detailsDAO
        allDetails = canadaInfoDao.getAllDetails()
    }

    fun insertCanadaInfoToDB() {
        CanadaApi.retrofitService.getCanadaInfo().enqueue(object : retrofit2.Callback<CanadaInfo> {
            override fun onFailure(call: Call<CanadaInfo>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

            override fun onResponse(call: Call<CanadaInfo>, response: Response<CanadaInfo>) {

                var canadainfo: CanadaInfo? = response.body()
                Log.d(TAG, "Got response")

                canadaInfoDao.insert(canadainfo)
                detailsDao.insert(canadainfo?.rows)
            }

        })

    }

}