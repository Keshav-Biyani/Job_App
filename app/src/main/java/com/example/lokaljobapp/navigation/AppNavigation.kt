package com.example.lokaljobapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lokaljobapp.ui.bookmark.BookMarkScreen
import com.example.lokaljobapp.ui.joblist.JobListScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = BottomNavItem.Jobs.route
    ) {
        composable(BottomNavItem.Jobs.route) {
            JobListScreen(navController)
        }
        composable(BottomNavItem.BookMark.route) {
            BookMarkScreen()
        }
    }
}