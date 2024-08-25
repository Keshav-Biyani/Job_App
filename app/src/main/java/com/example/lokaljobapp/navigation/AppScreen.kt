package com.example.lokaljobapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreen() {
}
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Jobs : BottomNavItem("home", Icons.Default.Home, "Job")
    data object BookMark : BottomNavItem("search", Icons.Filled.Bookmarks, "BookMark")
    //object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}