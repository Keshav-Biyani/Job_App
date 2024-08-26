package com.example.lokaljobapp.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lokaljobapp.api.response.Result

class SharedViewModel : ViewModel() {
    val jobData = mutableStateOf<Result?>(null)
    fun updatejobData(data : Result){
        jobData.value = data
    }

}