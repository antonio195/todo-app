package com.antoniocostadossantos.todoapp.ui.viewtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.antoniocostadossantos.todoapp.R
import com.antoniocostadossantos.todoapp.databinding.DialogObservationTaskBinding
import com.antoniocostadossantos.todoapp.model.Task
import com.antoniocostadossantos.todoapp.util.TASK_ENTITY

class ViewObservationTaskDialog : DialogFragment() {

    private lateinit var binding: DialogObservationTaskBinding
    private lateinit var task: Task

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogObservationTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getParcelable(TASK_ENTITY) ?: Task()
        binding.observationText.text = task.observation

        binding.dividerLive.setBackgroundColor(resources.getColor(R.color.black))
    }

}