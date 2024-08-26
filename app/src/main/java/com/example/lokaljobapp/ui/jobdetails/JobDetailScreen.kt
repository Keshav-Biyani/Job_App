package com.example.lokaljobapp.ui.jobdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lokaljobapp.api.response.JobTag
import com.example.lokaljobapp.api.response.Result
import com.example.lokaljobapp.ui.viewModel.BookMarkViewModel
import com.example.lokaljobapp.ui.viewModel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(
    sharedViewModel: SharedViewModel,
    bookMarkViewModel: BookMarkViewModel
) {
    val job = sharedViewModel.jobData.value!!

    JobDetailsScreen(jobDetails = job,bookMarkViewModel)

    
}
@Composable
fun JobDetailsScreen(jobDetails: Result, bookMarkViewModel: BookMarkViewModel) {
    val bookmarks by bookMarkViewModel.bookmarks.collectAsState()
    val isBookmarked = bookmarks.any { it.id == jobDetails.id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Job Title and Company Name
        Text(
            text = jobDetails.title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = jobDetails.company_name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Job Details Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f), // Allow this Row to take up remaining space
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = "Location", modifier = Modifier.weight(0.15f), tint = Color(0xFF2196F3))
                Text(text = jobDetails.primary_details.Place, modifier = Modifier.weight(0.35f), style = MaterialTheme.typography.bodyMedium)

                Icon(Icons.Default.Money, contentDescription = "Salary", modifier = Modifier.weight(0.15f), tint = Color.Gray)
                Text(text = jobDetails.primary_details.Salary, modifier = Modifier.weight(0.35f), style = MaterialTheme.typography.bodyMedium)
            }

            // Bookmark Icon
            IconButton(
                onClick = {
                    bookMarkViewModel.onBookmarkClick(jobDetails, isBookmarked)
                },
                modifier = Modifier
                    .size(24.dp) // Ensure the icon has a fixed size
                    .align(Alignment.CenterVertically) // Align icon vertically
            ) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = if (isBookmarked) Color(0xFF0E56A8) else Color.Gray
                )
            }
        }

        // Detailed Sections
        JobDetailSection("Job Type", jobDetails.primary_details.Job_Type)
        JobDetailSection("Experience", jobDetails.primary_details.Experience)
        JobDetailSection("Qualification", jobDetails.primary_details.Qualification)
        JobDetailSection("Vacancies", jobDetails.job_tags.firstOrNull()?.value ?: "")
        JobDetailSection("Job Role", jobDetails.job_role)

        // Contact HR Button
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /* Handle call to HR */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0E56A8)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "📞 Call HR", color = Color.White)
        }
    }
}


@Composable
fun JobDetailSection(label: String, detail: String) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = detail,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

//Scaffold(
//topBar = {
//    TopAppBar(
//        title = { Text(text = job.title, style = MaterialTheme.typography.titleLarge) },
//        actions = {
//            IconButton(onClick = {
//                bookMarkViewModel.onBookmarkClick(job,isBookmarked)
//            }) {
//
//                Icon(
//                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
//                    contentDescription = "Bookmark",
//                    tint = if (isBookmarked) Color.Blue else Color.Gray
//                )
//            }
//        }
//    )
//}
//) { paddingValues ->
//    Column(
//        modifier = Modifier
//            .padding(paddingValues)
//            .fillMaxSize()
//            .background(color = Color.White)
//            .padding(16.dp)
//    ) {
//        // Company Name
//        Text(
//            text = job.company_name,
//            style = MaterialTheme.typography.bodyMedium,
//            color = Color.Gray
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Job Title
//        Text(
//            text = job.title,
//            style = MaterialTheme.typography.headlineMedium,
//            color = Color.Black,
//            modifier = Modifier.padding(bottom = 4.dp)
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Location
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Icon(
//                imageVector = Icons.Default.LocationOn,
//                contentDescription = "Location",
//                tint = Color.Gray
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            Text(
//                text = job.primary_details.Place,
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Gray
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Salary
//        Text(
//            text = "Salary: ${job.primary_details.Salary}",
//            style = MaterialTheme.typography.bodyLarge,
//            color = Color.Black
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Experience
//        Text(
//            text = "Experience: ${job.primary_details.Experience}",
//            style = MaterialTheme.typography.bodyMedium,
//            color = Color.Gray
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Job Type
//        Text(
//            text = "Job Type: ${job.primary_details.Job_Type}",
//            style = MaterialTheme.typography.bodyMedium,
//            color = Color.Gray
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Apply Button
//        Button(
//            onClick = {
//
//            },
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
//        ) {
//            Text(text = "Apply Now", color = Color.White)
//        }
//    }
//}