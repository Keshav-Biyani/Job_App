package com.example.lokaljobapp.ui.bookmark

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.lokaljobapp.api.response.Result
import com.example.lokaljobapp.navigation.AppScreen
import com.example.lokaljobapp.ui.joblist.JobCard
import com.example.lokaljobapp.ui.joblist.JobsScreenViewModel
import com.example.lokaljobapp.ui.viewModel.BookMarkViewModel
import com.example.lokaljobapp.ui.viewModel.SharedViewModel

@Composable
fun BookMarkScreen(navController: NavHostController,bookMarkViewModel: BookMarkViewModel,sharedViewModel: SharedViewModel){
    val bookmarks by bookMarkViewModel.bookmarks.collectAsState()
val jobList = bookmarks
    Log.e("HELLO","HELLO123Scree")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(jobList.size) { index ->
            val job = jobList[index]

            val isBookmarked = bookmarks.any { it.id == job.id }
            Log.e("JobCheck", "Job: ${job.jobData.title}, isBookmarked: $isBookmarked")
            JobCard(job.jobData,
                isBookmarked = isBookmarked,
                onBookmarkClick = {
                    bookMarkViewModel.onBookmarkClick(job.jobData,isBookmarked)
                    Log.e("BookMark",isBookmarked.toString())

                }){
                sharedViewModel.updatejobData(job.jobData)
                navController.navigate(AppScreen.JobDetailScreen.route)

            }



        }



    }
}
