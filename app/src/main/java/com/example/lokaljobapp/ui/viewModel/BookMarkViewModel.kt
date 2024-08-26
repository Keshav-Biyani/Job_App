package com.example.lokaljobapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokaljobapp.api.response.Result
import com.example.lokaljobapp.room.JobDatabase
import com.example.lokaljobapp.room.JobBookMarkRepository
import com.example.lokaljobapp.room.data.JobBookMarkData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookMarkViewModel (application: Application) : AndroidViewModel(application) {

    private val bookmarkRepository: JobBookMarkRepository
    private val _bookmarks = MutableStateFlow<List<JobBookMarkData>>(emptyList())
    val bookmarks: StateFlow<List<JobBookMarkData>> = _bookmarks.asStateFlow()


    init {
        val bookmarkDao = JobDatabase.getInstance(application).dao()
        bookmarkRepository = JobBookMarkRepository(bookmarkDao)
        // Load the bookmarks when the ViewModel is created
        viewModelScope.launch {
            bookmarkRepository.getAllBookmarks().collect { bookmarkedJobs ->
                _bookmarks.value = bookmarkedJobs
            }
        }


    }
    // Add a job to the bookmarks
    fun addBookmark(job: JobBookMarkData) {
        viewModelScope.launch {
            bookmarkRepository.insertBookmark(job)
        }
    }

    // Remove a job from the bookmarks
    fun removeBookmark(job: JobBookMarkData) {
        viewModelScope.launch {
            bookmarkRepository.deleteBookmark(job)
        }
    }

    // Check if a specific job is bookmarked
    suspend fun isJobBookmarked(jobId: Int): Boolean {
        return bookmarkRepository.isBookmarked(jobId)
    }


    fun onBookmarkClick(job: Result, isBookmarked: Boolean) {
        viewModelScope.launch {
            if (isBookmarked) {
                removeBookmark(JobBookMarkData(job.id,job))
            } else {
                addBookmark(JobBookMarkData(job.id,job))
            }
        }
    }


}