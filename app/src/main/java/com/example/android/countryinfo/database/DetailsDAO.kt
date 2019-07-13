package com.example.android.countryinfo.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.android.countryinfo.model.CanadaInfo
import com.example.android.countryinfo.model.Details

@Dao
interface DetailsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(details: List<Details>?)

    @Query("DELETE FROM canada_info_details")
    fun deleteAllNotes()

    @Query("SELECT * FROM canada_info_details ")
    fun getAllDetails(): LiveData<List<Details>>
}