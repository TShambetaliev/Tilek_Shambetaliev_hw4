package com.example.tilek_shambetaliev_hw4.data.local.db

import androidx.room.*
import com.example.tilek_shambetaliev_hw4.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    //сортировка ASK от А до Я
    //сортировка DESC от Я до А

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): List<Task>

}