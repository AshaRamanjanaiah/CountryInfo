package com.example.android.countryinfo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.android.countryinfo.model.CanadaInfo
import com.example.android.countryinfo.model.Details
import com.example.android.countryinfo.viewmodel.CanadaInfoViewModel
import com.example.android.countryinfo.viewmodel.CanadaInfoViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName

    private lateinit var canadaInfoViewModel: CanadaInfoViewModel

    private lateinit var canadaInfoRecyclerview: RecyclerView
    private lateinit var canadaInfoAdapter: CanadaInfoAdapter

    private var canadaDetails = listOf<Details>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        canadaInfoRecyclerview = findViewById<RecyclerView>(R.id.rv_canada_info).apply {

            setHasFixedSize(true)

            layoutManager = linearLayoutManager

        }

        val viewModelFactory = CanadaInfoViewModelFactory(application)

        // Get a reference to the ViewModel associated with this Activity.
        canadaInfoViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(CanadaInfoViewModel::class.java)

        canadaInfoViewModel.getAllDetails().observe(this,
            Observer<List<Details>> { t -> gotDataUpdateUI(t!!) })

        canadaInfoViewModel.getTitleInfo().observe(this,
            Observer<List<CanadaInfo>> { t -> updateTitle(t!!) })

    }

    private fun updateTitle(titleInfo: List<CanadaInfo>) {
        if(titleInfo.size != 0){
            var title = titleInfo.get(0).title
            setTitle(title)
        }
    }

    private fun insertDataToDB() {
        // fetch data on IO thread
        (GlobalScope.launch(Dispatchers.Main) {
            canadaInfoViewModel.insert()
        })
    }

    private fun gotDataUpdateUI(details: List<Details>) {
        if(details.size == 0){
            insertDataToDB()
        }
        Log.d(TAG, "Data updated")
        canadaDetails = details
        canadaInfoAdapter = CanadaInfoAdapter(this, details)
        canadaInfoRecyclerview.adapter = canadaInfoAdapter

    }

    fun refreshData(view: View){
        insertDataToDB()
        Log.d(TAG, "Updated data in DB")
    }

}
