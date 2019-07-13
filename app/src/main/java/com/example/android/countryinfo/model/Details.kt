package com.example.android.countryinfo.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

//@Entity(tableName = "canada_info_details", indices = arrayOf(Index(value = ["subtitle"], unique = true)))
@Entity(tableName = "canada_info_details")
data class Details(
    @PrimaryKey(autoGenerate = true)
    val infoId: Int,
    @ColumnInfo(name = "subtitle")
    val title: String?,
    val description: String?,
    val imageHref: String?
)