package com.naszi.mobilapp.to_dolistapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TaskDao() {
    @Query("SELECT * FROM `tasks-table` ORDER BY `tasks-id` DESC")
    abstract fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addTask(task: Task)

    @Delete
    abstract suspend fun deleteTask(task: Task)

    @Update
    abstract suspend fun updateTask(task: Task)
}