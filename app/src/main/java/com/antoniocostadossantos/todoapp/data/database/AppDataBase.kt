package com.antoniocostadossantos.todoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antoniocostadossantos.todoapp.data.database.dao.TaskDao
import com.antoniocostadossantos.todoapp.model.Task

@Database(entities = [Task::class], version = 2)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskModelDao(): TaskDao

    companion object {
        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, "tasks_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}