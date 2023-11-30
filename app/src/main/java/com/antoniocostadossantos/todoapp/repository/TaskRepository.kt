package com.antoniocostadossantos.todoapp.repository

import com.antoniocostadossantos.todoapp.data.database.dao.TaskDao
import com.antoniocostadossantos.todoapp.model.Task

class TaskRepository(private val taskDao: TaskDao) {


    suspend fun getAll() = taskDao.getAll()


    suspend fun getAll(concluded: Boolean) = taskDao.getAll(concluded)


    suspend fun newTask(task: Task) = taskDao.newTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    suspend fun getTaskById(id: Int): Task {
        TODO("Not yet implemented")
    }

}