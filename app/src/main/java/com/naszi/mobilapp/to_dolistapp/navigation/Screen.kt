package com.naszi.mobilapp.to_dolistapp.navigation

sealed class Screen(val route: String) {
    data object HomeScreen: Screen(route = "home_screen")
    data object AddScreen: Screen(route = "add_screen")
}