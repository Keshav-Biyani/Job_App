package com.example.lokaljobapp.ui.joblist

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.lokaljobapp.MainActivity
import com.example.lokaljobapp.R
import com.example.lokaljobapp.api.response.Result

inline fun <reified T : ViewModel> Context.getViewModel() =
    ViewModelProvider(this as MainActivity)[T::class.java]
@Composable
fun JobListScreen(navController: NavHostController){
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

            SuccessScreen(navController = navController,jobList ,viewModel)


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
                modifier = Modifier.clickable { navController.popBackStack() },
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
    viewModel: JobsScreenViewModel
) {

    Log.e("HELLO","HELLO123Scree")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(jobList.size) { index ->
            JobItem(job = jobList[index])
if(index>= jobList.size -1){
viewModel.getJobList()
}
        }



    }

}
@Composable
fun JobItem(job: Result) {
    // Your UI for displaying a job item
   // Text(text = job.title ?: "hello", color = Color.Black)
    Box(
        contentAlignment = Center,
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(1f) // Making it a square
            .background(Color.Magenta)
            .clickable {

            }
    ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = job.title ?: "Hello",
                    // fontFamily = RobotoCondensed,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = job.primary_details.Salary ?: "Hello",
                    // fontFamily = RobotoCondensed,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = job.primary_details.Place ?: "Hello",
                    // fontFamily = RobotoCondensed,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Forward",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
