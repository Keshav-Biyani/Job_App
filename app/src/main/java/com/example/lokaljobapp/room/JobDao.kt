package com.example.lokaljobapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lokaljobapp.room.data.JobBookMarkData
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Insert
    suspend fun insertBookMarkData(jobBookMarkData: JobBookMarkData)

    @Delete
    suspend fun deleteBookMarkData(jobBookMarkData: JobBookMarkData)

    @Query("SELECT * FROM bookmark_table WHERE id = :jobId")
    suspend fun getBookmarkById(jobId: Int): JobBookMarkData?

    @Query("SELECT * FROM bookmark_table")
    fun getAllBookmarks(): Flow<List<JobBookMarkData>>
}