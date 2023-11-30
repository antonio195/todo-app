package com.antoniocostadossantos.todoapp.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoniocostadossantos.todoapp.model.Task
import com.antoniocostadossantos.todoapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _concludedTasks = MutableLiveData<List<Task>>()
    val concludedTasks: LiveData<List<Task>> = _concludedTasks

    private val _notConcludedTasks = MutableLiveData<List<Task>>()
    val notConcludedTasks: LiveData<List<Task>> = _notConcludedTasks

    fun getAllTasks(concluded: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.getAll(concluded).collect {
            when (concluded) {
                true -> {
                    _concludedTasks.postValue(it)
                }

                false -> {
                    _notConcludedTasks.postValue(it)
                }
            }
        }
    }

    fun checkTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        task.apply {
            checked = !checked
        }
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.deleteTask(task)
    }

}