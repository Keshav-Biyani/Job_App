package com.example.lokaljobapp.ui.joblist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokaljobapp.api.RetrofitInstance
import com.example.lokaljobapp.api.response.Job_Response
import com.example.lokaljobapp.api.response.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class JobsScreenUiState {
    data object Loading : JobsScreenUiState()
    data class Success(val joblist : Job_Response) : JobsScreenUiState()
    data class Error(val error: String) : JobsScreenUiState()
}
class JobsScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<JobsScreenUiState>(JobsScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var currentPage = 1
    private var endReached = false

    fun getJobList(){
        viewModelScope.launch(Dispatchers.Main) {
            try {


                val response = RetrofitInstance.apiService.getJobs(currentPage)
                val currentList =
                    (_uiState.value as? JobsScreenUiState.Success)?.joblist?.results ?: emptyList()

                if (response.body()?.results?.isEmpty() == true) {
                    endReached = true
                } else {
                    //   _jobs.value += response.body()?.results!!
                    val updatedList = currentList + response.body()?.results!!
                    Log.e("resilt",updatedList.toString())
                    _uiState.value = JobsScreenUiState.Success(Job_Response(updatedList))
                    currentPage++


                }
            }catch (e : Exception){
                _uiState.value = JobsScreenUiState.Error(e.localizedMessage ?: "An error occurred")

            }
        }
    }

}