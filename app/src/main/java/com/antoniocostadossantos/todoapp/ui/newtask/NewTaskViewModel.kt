package com.antoniocostadossantos.todoapp.ui.newtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniocostadossantos.todoapp.model.Task
import com.antoniocostadossantos.todoapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun newTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.newTask(task)
    }

}