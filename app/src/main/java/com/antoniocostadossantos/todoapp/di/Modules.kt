package com.antoniocostadossantos.todoapp.di

import com.antoniocostadossantos.todoapp.data.database.AppDataBase
import com.antoniocostadossantos.todoapp.repository.TaskRepository
import com.antoniocostadossantos.todoapp.ui.newtask.NewTaskViewModel
import com.antoniocostadossantos.todoapp.ui.tasks.TasksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { AppDataBase.getInstance(androidContext()) }
    single { TaskRepository(AppDataBase.getInstance(androidContext()).taskModelDao()) }
    viewModel { TasksViewModel(get()) }
    viewModel { NewTaskViewModel(get()) }

}