package com.antoniocostadossantos.todoapp.ui.newtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antoniocostadossantos.todoapp.databinding.DialogNewTaskBinding
import com.antoniocostadossantos.todoapp.model.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTaskBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: DialogNewTaskBinding
    private val newTaskViewModel: NewTaskViewModel by viewModel()
    private lateinit var task: Task
    private var shouldUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogNewTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUpdateTask()
        saveTask()
    }

    private fun checkUpdateTask() {
        val shouldUpdateTask = arguments?.getBoolean("UPDATE_TASK") ?: false
        if (shouldUpdateTask) setupUpdate() else task = Task()
    }

    private fun setupUpdate() {
        shouldUpdate = true
        task = arguments?.getParcelable("TASK_ENTITY") ?: Task()

        binding.taskTitleInput.setText(task.title)
        binding.title.text = "Update Task"
    }

    private fun saveTask() {
        binding.btnSaveNewTask.setOnClickListener {
            task.title = binding.taskTitleInput.text.toString()
            newTaskViewModel.newTask(task)
            dismiss()
        }
    }
}