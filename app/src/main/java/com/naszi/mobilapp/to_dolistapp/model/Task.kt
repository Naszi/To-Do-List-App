package com.naszi.mobilapp.to_dolistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks-table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tasks-id")
    val id: Int = 0,
    @ColumnInfo(name = "tasks-title")
    val title: String,
    @ColumnInfo(name = "tasks-description")
    val description: String,
    @ColumnInfo(name = "tasks-is-completed")
    val isCompleted: Boolean = false
)
