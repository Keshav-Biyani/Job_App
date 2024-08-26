package com.example.lokaljobapp.room

import com.example.lokaljobapp.room.data.JobBookMarkData
import kotlinx.coroutines.flow.Flow

class JobBookMarkRepository(private  val dao : JobDao) {
    fun getAllBookmarks(): Flow<List<JobBookMarkData>> {
        return dao.getAllBookmarks()
    }

    // Add a bookmark
    suspend fun insertBookmark(bookmark: JobBookMarkData) {
        dao.insertBookMarkData(bookmark)
    }

    // Remove a bookmark
    suspend fun deleteBookmark(bookmark: JobBookMarkData) {
        dao.deleteBookMarkData(bookmark)
    }

    // Check if a job is bookmarked
    suspend fun isBookmarked(jobId: Int): Boolean {
        return dao.getBookmarkById(jobId) != null
    }

}