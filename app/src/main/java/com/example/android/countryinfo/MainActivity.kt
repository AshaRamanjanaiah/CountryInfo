package com.example.android.countryinfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.countryinfo.repository.CanadaInfoRepository

class MainActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataFromNetworkAndInsertToDB()
    }

    private fun getDataFromNetworkAndInsertToDB() {

        val canadaInfoRepository = CanadaInfoRepository(application)
        canadaInfoRepository.insertCanadaInfoToDB()

    }
}
