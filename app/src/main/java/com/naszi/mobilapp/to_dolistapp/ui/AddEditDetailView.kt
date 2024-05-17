package com.naszi.mobilapp.to_dolistapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.naszi.mobilapp.to_dolistapp.R
import com.naszi.mobilapp.to_dolistapp.model.Task
import com.naszi.mobilapp.to_dolistapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: MainViewModel,
    navController: NavController
) {
    val snackMessage = remember { mutableStateOf("") }
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
    Scaffold(
        topBar = {
            AppBarView(
                title = if (id != 0L) "Update Task" else "Add Task"
            ) { navController.navigateUp() }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            TaskTextField(
                label = "Title",
                value = viewModel.taskTitleState,
                onValueChanged = {onValueChanged -> viewModel.onTaskTitleChanged(onValueChanged) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TaskTextField(
                label = "Description",
                value = viewModel.taskDescriptionState,
                onValueChanged = {onValueChanged -> viewModel.onTaskDescriptionChanged(onValueChanged) }
            )
            Button(
                onClick = {
                    if (viewModel.taskTitleState.isNotEmpty() && viewModel.taskDescriptionState.isNotEmpty()) {
                        if (id != 0L) {
                            viewModel.updateTask(
                                Task(
                                    id = id,
                                    title = viewModel.taskTitleState.trim(),
                                    description = viewModel.taskDescriptionState.trim()
                                )
                            )
                            snackMessage.value = "Task has been updated"
                        } else {
                            viewModel.addTask(
                                Task(
                                    title = viewModel.taskTitleState.trim(),
                                    description = viewModel.taskDescriptionState.trim()
                                )
                            )
                            snackMessage.value = "Task has been created"
                        }
                    } else {
                        snackMessage.value = "Enter fields to create a task"
                    }
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }
                }
            ) {
                Text(
                    text = if (id != 0L) "Update Task" else "Add Task",
                    style = TextStyle(fontSize = 18.sp)
                )

            }
        }
    }
}

@Composable
fun TaskTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
        )
    )
}
