package com.example.lokaljobapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreen(val route: String) {
    data object SplashScreen : AppScreen("splash_screen")
    data object MainScreen : AppScreen("main_screen")
    data object JobDetailScreen : AppScreen("Job_Detail_Screen")

}
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Jobs : BottomNavItem("jobs", Icons.Default.LocalMall, "Jobs")
    data object BookMark : BottomNavItem("bookmark", Icons.Filled.Bookmarks, "BookMark")

}