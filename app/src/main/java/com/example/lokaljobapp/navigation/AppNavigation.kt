package com.example.lokaljobapp.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lokaljobapp.MainScreen
import com.example.lokaljobapp.ui.bookmark.BookMarkScreen
import com.example.lokaljobapp.ui.jobdetails.JobDetailsScreen
import com.example.lokaljobapp.ui.joblist.JobListScreen
import com.example.lokaljobapp.ui.splash.SplashScreen
import com.example.lokaljobapp.ui.viewModel.BookMarkViewModel
import com.example.lokaljobapp.ui.viewModel.SharedViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val context = LocalContext.current
    val bookMarkViewModel = BookMarkViewModel(context.applicationContext as Application)
    val sharedViewModel : SharedViewModel = viewModel()

    NavHost(
        navController = navController, startDestination = AppScreen.SplashScreen.route
    ) {

        composable(AppScreen.SplashScreen.route) {
            SplashScreen {
                // Navigate to JobList screen after splash
                navController.navigate(BottomNavItem.Jobs.route) {
                    // Clear splash screen from back stack
                    popUpTo(AppScreen.SplashScreen.route) { inclusive = true }
                }
            }
        }
        composable(BottomNavItem.Jobs.route) {
            JobListScreen(navController,bookMarkViewModel,sharedViewModel)
        }
        composable(BottomNavItem.BookMark.route) {
            BookMarkScreen(navController,bookMarkViewModel,sharedViewModel)
        }
        composable(AppScreen.JobDetailScreen.route){
            JobDetailsScreen(sharedViewModel,bookMarkViewModel){
                navController.popBackStack()
            }
        }
    }
}