package com.naszi.mobilapp.to_dolistapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naszi.mobilapp.to_dolistapp.model.Graph
import com.naszi.mobilapp.to_dolistapp.model.Task
import com.naszi.mobilapp.to_dolistapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val taskRepository: TaskRepository = Graph.taskRepository
): ViewModel() {
    var taskTitleState by mutableStateOf("")
    var taskDescriptionState by mutableStateOf("")

    fun onTaskTitleChanged(newTile: String) {
        taskTitleState = newTile
    }

    fun onTaskDescriptionChanged(newDescription: String) {
        taskDescriptionState = newDescription
    }

    lateinit var getAllTasks: Flow<List<Task>>
    init {
        viewModelScope.launch {
            getAllTasks = taskRepository.getAllTask()
        }
    }

    fun getTaskById(id: Long): Flow<Task> {
        return taskRepository.getTaskById(id)
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}