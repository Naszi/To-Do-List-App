package com.naszi.mobilapp.to_dolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.naszi.mobilapp.to_dolistapp.navigation.Navigation
import com.naszi.mobilapp.to_dolistapp.repository.Injection
import com.naszi.mobilapp.to_dolistapp.repository.UserRepository
import com.naszi.mobilapp.to_dolistapp.ui.theme.ToDoListAppTheme
import com.naszi.mobilapp.to_dolistapp.viewmodel.AuthViewModel
import com.naszi.mobilapp.to_dolistapp.viewmodel.factory.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userRepository = UserRepository(
                FirebaseAuth.getInstance(),
                Injection.instance()
            )
            val viewModelFactory = ViewModelFactory(
                mapOf(
                    AuthViewModel::class.java to { AuthViewModel(userRepository) }
                )
            )
            ToDoListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(authViewModel = viewModel(factory = viewModelFactory))
                }
            }
        }
    }
}
