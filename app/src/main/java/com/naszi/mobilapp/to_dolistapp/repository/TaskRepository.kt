package com.naszi.mobilapp.to_dolistapp.repository

import com.naszi.mobilapp.to_dolistapp.model.Task
import com.naszi.mobilapp.to_dolistapp.model.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskDao: TaskDao
) {
    fun getAllTask(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    fun getTaskById(id: Long): Flow<Task> {
        return taskDao.getTaskById(id)
    }

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}