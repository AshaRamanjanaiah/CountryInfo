package com.example.android.countryinfo.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.android.countryinfo.database.CanadaInfoDao
import com.example.android.countryinfo.database.AppDatabase
import com.example.android.countryinfo.database.DetailsDAO
import com.example.android.countryinfo.model.CanadaInfo
import com.example.android.countryinfo.model.Details
import com.example.android.countryinfo.network.CanadaApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

class CanadaInfoRepository(application: Application) {

    private var canadaInfoDao: CanadaInfoDao
    private var detailsDao: DetailsDAO

    private val TAG: String = this::class.java.simpleName

    private var allDetails: LiveData<List<Details>>

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)
        canadaInfoDao = database.canadaInfoDao
        detailsDao = database.detailsDAO
        allDetails = detailsDao.getAllDetails()
    }

    private val repositoryJob = Job()

/**
 * A [CoroutineScope] keeps track of all coroutines started by this Repository.
 *
 * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
 * the main thread on Android. This is a sensible default because most coroutines started by
 * a [repositoryJob] update the UI after performing some processing.
 * */

    private val uiScope = CoroutineScope(Dispatchers.Main + repositoryJob)

    fun cancelJob(){
        repositoryJob.cancel()
    }

    /**
     * Inserts data in to database.
     *
     */

    fun insert(){
        getDataFromNetwork()
    }

    /**
     * Gets Livedata from database.
     *
     */

    fun getAllDetails(): LiveData<List<Details>>{
        return allDetails
    }

    /**
     * Gets Livedata from database.
     *
     */

    fun getDataFromNetwork(){
        CanadaApi.retrofitService.getCanadaInfo().enqueue(object : retrofit2.Callback<CanadaInfo> {
            override fun onFailure(call: Call<CanadaInfo>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

            override fun onResponse(call: Call<CanadaInfo>, response: Response<CanadaInfo>) {

                var canadainfo: CanadaInfo? = response.body()
                Log.d(TAG, "Got Data from network")
                GlobalScope.launch(Dispatchers.Main) {
                    insertCanadaInfoToDB(canadainfo)
                }
            }

        })
    }

    suspend fun insertCanadaInfoToDB(canadainfo: CanadaInfo?) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                canadaInfoDao.insert(canadainfo)
                detailsDao.insert(canadainfo?.rows)
                Log.d(TAG, "Data inserted in to DB")
            }
        }
    }

}