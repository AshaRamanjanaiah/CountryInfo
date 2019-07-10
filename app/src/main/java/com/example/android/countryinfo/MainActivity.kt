package com.example.android.countryinfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.countryinfo.model.CanadaInfo
import com.example.android.countryinfo.network.CanadaApi
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataFromNetwork()
    }

    private fun getDataFromNetwork() {

            CanadaApi.retrofitService.getCanadaInfo().enqueue(object : retrofit2.Callback<CanadaInfo> {
                override fun onFailure(call: Call<CanadaInfo>, t: Throwable) {
                    Log.d(TAG, t.toString())
                }

                override fun onResponse(call: Call<CanadaInfo>, response: Response<CanadaInfo>) {

                    var canadainfo: CanadaInfo? = response.body()
                    Log.d(TAG, "Got response")
                }

            })

    }
}
