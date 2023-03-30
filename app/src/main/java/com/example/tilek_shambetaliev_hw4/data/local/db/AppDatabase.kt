package com.example.tilek_shambetaliev_hw4.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tilek_shambetaliev_hw4.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}