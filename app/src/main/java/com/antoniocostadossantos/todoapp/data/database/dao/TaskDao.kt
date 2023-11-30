package com.antoniocostadossantos.todoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.antoniocostadossantos.todoapp.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE checked =:concluded")
    fun getAll(concluded: Boolean): Flow<List<Task>>

    @Upsert
    fun newTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM Task WHERE id=:id")
    fun getTaskById(id: Int): Task

}