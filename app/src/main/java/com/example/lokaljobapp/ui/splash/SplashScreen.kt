package com.example.lokaljobapp.ui.splash
// SplashScreen.kt
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.lokaljobapp.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Display your splash screen content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Example image and text
        Image(painter = painterResource(id = R.drawable.job_app_icon), contentDescription = null)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome to Lokal Job App",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "An initiative by Lokal Organization",
            style = MaterialTheme.typography.bodyMedium
        )
    }

    // Navigate to main screen after delay
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000) // Splash screen delay
        onTimeout()
    }
}
