package com.naszi.mobilapp.to_dolistapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.navArgument
import com.naszi.mobilapp.to_dolistapp.ui.AddEditDetailView
import com.naszi.mobilapp.to_dolistapp.ui.HomeView
import com.naszi.mobilapp.to_dolistapp.ui.login.LoginScreen
import com.naszi.mobilapp.to_dolistapp.ui.login.SignUpScreen
import com.naszi.mobilapp.to_dolistapp.viewmodel.MainViewModel

@Composable
fun Navigation(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeView(viewModel, navController)
        }
        composable(
            Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {entry ->
            val id = if (entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = viewModel, navController = navController)
        }
        composable(Screen.SignupScreen.route) {
            SignUpScreen(
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) })
        }
    }
}