package com.naszi.mobilapp.to_dolistapp.navigation

sealed class Screen(val route: String) {
    data object HomeScreen: Screen(route = "home_screen")
    data object AddScreen: Screen(route = "add_screen")
    data object LoginScreen: Screen(route = "login_screen")
    data object SignupScreen: Screen(route = "signup_screen")
}