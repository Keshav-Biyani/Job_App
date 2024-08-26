package com.example.lokaljobapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.lokaljobapp.room.data.JobBookMarkData
import com.example.lokaljobapp.room.data.JobDataConverter

@Database(
    entities = [JobBookMarkData :: class],
    version = 1,
)
@TypeConverters(JobDataConverter :: class)
abstract class JobDatabase : RoomDatabase() {
    abstract  fun dao() : JobDao
    companion object {
        @Volatile
        private var INSTANCE: JobDatabase? = null

        fun getInstance(context: Context): JobDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JobDatabase::class.java,
                        "JobDatabase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}