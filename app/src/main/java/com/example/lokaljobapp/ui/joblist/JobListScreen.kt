package com.example.lokaljobapp.ui.joblist

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.lokaljobapp.MainActivity
import com.example.lokaljobapp.R
import com.example.lokaljobapp.api.response.Result
import com.example.lokaljobapp.navigation.AppScreen
import com.example.lokaljobapp.navigation.BottomNavItem
import com.example.lokaljobapp.ui.viewModel.BookMarkViewModel
import com.example.lokaljobapp.ui.viewModel.SharedViewModel

inline fun <reified T : ViewModel> Context.getViewModel() =
    ViewModelProvider(this as MainActivity)[T::class.java]
@Composable
fun JobListScreen(navController: NavHostController,bookMarkViewModel: BookMarkViewModel,sharedViewModel: SharedViewModel){
    val viewModel = LocalContext.current.getViewModel<JobsScreenViewModel>()
    val uiState: JobsScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.getJobList() }
    when (val state = uiState) {
        is JobsScreenUiState.Loading -> LoadingScreen()
        is JobsScreenUiState.Error -> ErrorScreen(navController, state.error)
        is JobsScreenUiState.Success -> {
            Log.e("HELLO","HELLOScree")
            val jobList = (uiState as JobsScreenUiState.Success).joblist.results
            Log.e("HELLO",jobList.toString())

            SuccessScreen(navController = navController,jobList ,viewModel,bookMarkViewModel,sharedViewModel)


        }
    }
}
@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
private fun ErrorScreen(navController: NavHostController, error: String = "") {
    AlertDialog(onDismissRequest = { },
        title = { Text(stringResource(R.string.error_occurred)) },
        text = { Text(error) },
        confirmButton = {
            Text(
                modifier = Modifier.clickable { navController.navigate(BottomNavItem.BookMark.route) },
                text = stringResource(R.string.accept),
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "Error Icon"
            )
        })
}
@Composable
private fun SuccessScreen(
    navController: NavHostController,
    jobList: List<Result>,
    viewModel: JobsScreenViewModel,
    bookMarkViewModel: BookMarkViewModel,
    sharedViewModel: SharedViewModel
) {
    val bookmarks by bookMarkViewModel.bookmarks.collectAsState()

    Log.e("HELLO","HELLO123Scree")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(jobList.size) { index ->
            val job = jobList[index]

            val isBookmarked = bookmarks.any { it.id == job.id }
            Log.e("JobCheck", "Job: ${job.title}, isBookmarked: $isBookmarked")
            JobCard(job,
                isBookmarked = isBookmarked,
                onBookmarkClick = {
                    bookMarkViewModel.onBookmarkClick(jobList[index],isBookmarked)
                    Log.e("BookMark",isBookmarked.toString())

                }){
sharedViewModel.updatejobData(job)
                navController.navigate(AppScreen.JobDetailScreen.route)
            }

            if(index>= jobList.size -1){
                viewModel.getJobList()
            }

        }



    }

}
@Composable
fun JobCard(
    job: Result,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onCardClick:()->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onCardClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Job Title
                Text(
                    text = job.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold // Bold font similar to LinkedIn
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Location Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = Color(0xFF2196F3), // Blue color for the icon
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 4.dp)
                    )
                    Text(
                        text = job.primary_details.Place,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium // Medium weight for a clean look
                        ),
                        color = Color.Gray
                    )
                }

                // Salary
                Text(
                    text = job.primary_details.Salary,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Experience
                Text(
                    text = job.primary_details.Experience,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF0073B1) // LinkedIn blue for highlights
                    )
                )
            }

            // Bookmark Icon
            IconButton(
                onClick = onBookmarkClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = if (isBookmarked) Color(0xFF0E56A8) else Color.Gray
                )
            }
        }
    }
}



