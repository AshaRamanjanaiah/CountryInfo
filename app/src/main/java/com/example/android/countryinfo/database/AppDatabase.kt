package com.example.android.countryinfo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.android.countryinfo.model.CanadaInfo
import com.example.android.countryinfo.model.Details

@Database(entities = [CanadaInfo::class, Details::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val canadaInfoDao: CanadaInfoDao
    abstract val detailsDAO: DetailsDAO

   companion object{
       private var INSTANCE: AppDatabase? = null

       fun getInstance(context: Context): AppDatabase{
           synchronized(this){
               var instance = INSTANCE

               if(instance == null){
                   instance = Room.databaseBuilder(
                       context.applicationContext,
                       AppDatabase::class.java, "canada_info_db"
                   )
                       .fallbackToDestructiveMigration()
                       //.allowMainThreadQueries()
                       .build()
                   INSTANCE = instance
               }

               return instance
           }
       }
   }

}