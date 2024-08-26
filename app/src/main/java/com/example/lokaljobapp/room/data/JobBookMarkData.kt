package com.example.lokaljobapp.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.lokaljobapp.api.response.Result
import com.google.gson.Gson

@Entity(tableName = "bookmark_table")
data class JobBookMarkData(@PrimaryKey val id : Int , val  jobData : Result)

class JobDataConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromUserData(jobData: Result): String? {
        return gson.toJson(jobData)
    }

    @TypeConverter
    fun toUserData(jobDataString: String?): Result? {
        if (jobDataString == null) return null
        return gson.fromJson(jobDataString, Result::class.java)
    }
}
