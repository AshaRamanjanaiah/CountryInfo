package com.example.android.countryinfo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.countryinfo.model.Details
import com.example.android.countryinfo.viewmodel.CanadaInfoViewModel
import com.example.android.countryinfo.viewmodel.CanadaInfoViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName

    private lateinit var canadaInfoViewModel: CanadaInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = CanadaInfoViewModelFactory(application)

        // Get a reference to the ViewModel associated with this Activity.
        canadaInfoViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(CanadaInfoViewModel::class.java)

        insertDataToDB()

        canadaInfoViewModel.getAllDetails().observe(this,
            Observer<List<Details>> { t -> gotData(t!!) })
    }

    private fun insertDataToDB() {
        // fetch data on IO thread
        (GlobalScope.launch(Dispatchers.Main) {
            canadaInfoViewModel.insert()
        })
    }

    private fun gotData(details: List<Details>) {
        Log.d(TAG, "Data updated")
    }

}
