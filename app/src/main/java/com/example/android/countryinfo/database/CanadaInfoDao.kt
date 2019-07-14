package com.example.android.countryinfo.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.android.countryinfo.model.CanadaInfo

@Dao
interface CanadaInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(canadaInfo: CanadaInfo?)

    @Query("DELETE FROM canada_info")
    fun deleteAllNotes()

    @Query("SELECT * FROM canada_info")
    fun getTitle(): LiveData<List<CanadaInfo>>
}