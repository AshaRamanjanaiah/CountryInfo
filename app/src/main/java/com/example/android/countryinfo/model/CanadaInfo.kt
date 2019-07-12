package com.example.android.countryinfo.model

import android.arch.persistence.room.*

@Entity(tableName = "canada_info", indices = arrayOf(Index(value = ["title"], unique = true)))
data class CanadaInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String?,
    @Ignore
    val rows: List<Details>?
){
    constructor() : this(0, "UNKNOWN", emptyList())
}
