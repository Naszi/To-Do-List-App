package com.naszi.mobilapp.to_dolistapp.ui

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.naszi.mobilapp.to_dolistapp.model.Task
import com.naszi.mobilapp.to_dolistapp.viewmodel.MainViewModel

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: MainViewModel,
    navController: NavController
) {
    val snackMessage = remember { mutableSetOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0L) {
        val task = viewModel.getTaskById(id).collectAsState(initial = Task(0L, "", ""))
        viewModel.taskTitleState = task.value.title
        viewModel.taskDescriptionState = task.value.description
    } else {
        viewModel.taskTitleState = ""
        viewModel.taskDescriptionState = ""
    }
}
