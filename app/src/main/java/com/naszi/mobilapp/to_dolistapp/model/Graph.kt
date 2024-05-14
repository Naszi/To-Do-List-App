package com.naszi.mobilapp.to_dolistapp.model

import android.content.Context
import androidx.room.Room
import com.naszi.mobilapp.to_dolistapp.repository.TaskRepository

object Graph {
    lateinit var database: TaskDatabase
    val taskRepository by lazy {
        TaskRepository(taskDao = database.taskDao())
    }

    fun provider(context: Context) {
        database = Room.databaseBuilder(
            context = context,
            TaskDatabase::class.java,
            name = "tasks.db"
        ).build()
    }
}