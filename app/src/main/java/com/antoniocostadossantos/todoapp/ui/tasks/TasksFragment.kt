package com.antoniocostadossantos.todoapp.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.antoniocostadossantos.todoapp.R
import com.antoniocostadossantos.todoapp.databinding.FragmentTasksBinding
import com.antoniocostadossantos.todoapp.model.Task
import com.antoniocostadossantos.todoapp.ui.adapter.TasksAdapter
import com.antoniocostadossantos.todoapp.ui.newtask.NewTaskBottomSheet
import com.antoniocostadossantos.todoapp.ui.viewtask.ViewObservationTaskDialog
import com.antoniocostadossantos.todoapp.util.CONCLUDED
import com.antoniocostadossantos.todoapp.util.TASK_ENTITY
import com.antoniocostadossantos.todoapp.util.UPDATE_TASK
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksFragment : Fragment() {

    private lateinit var binding: FragmentTasksBinding
    private val tasksViewModel: TasksViewModel by viewModel()
    private lateinit var tasksAdapter: TasksAdapter
    private var concludedTasks = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBoolean()
        setupIconTopBar()
        setupTitle()
        setupRecyclerView()
        getAllTasks()
        observeFlow()
    }

    private fun getBoolean() {
        concludedTasks = arguments?.getBoolean(CONCLUDED) ?: false
    }

    private fun setupTitle() {
        binding.title.text = if (concludedTasks) {
            getString(R.string.concluded)
        } else {
            getString(R.string.not_concluded)
        }
    }

    private fun setupIconTopBar() {
        binding.topbarIcon.setImageDrawable(
            if (concludedTasks) {
                activity?.getDrawable(R.drawable.round_done_all_24)
            } else {
                activity?.getDrawable(R.drawable.round_remove_done_24)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        getAllTasks()
    }

    private fun getAllTasks() {
        tasksViewModel.getAllTasks(concludedTasks)
    }

    private fun setupRecyclerView() {
        tasksAdapter = TasksAdapter(
            checkTask = { checkTask(it) },
            clickTask = { clickTask(it) },
            updateTask = { updateTask(it) },
            deleteTask = { deleteTask(it) }
        )
        binding.rvTasks.apply {
            adapter = tasksAdapter
            setHasFixedSize(true)
        }
    }

    private fun clickTask(task: Task) {
        if (task.observation.isNotEmpty()) {
            val observationTask = ViewObservationTaskDialog()
            observationTask.arguments = Bundle().apply { putParcelable(TASK_ENTITY, task) }
            observationTask.show(childFragmentManager, "")
        } else {
            Toast.makeText(requireContext(), R.string.no_observations, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkTask(task: Task) {
        tasksViewModel.checkTask(task)
    }

    private fun observeFlow() {
        when (concludedTasks) {
            true -> tasksViewModel.concludedTasks.observe(viewLifecycleOwner) {
                tasksAdapter.setItems(it)
            }

            false -> tasksViewModel.notConcludedTasks.observe(viewLifecycleOwner) {
                tasksAdapter.setItems(it)
            }
        }
    }

    private fun updateTask(task: Task) {
        val bottomSheet = NewTaskBottomSheet()

        bottomSheet.arguments = Bundle().apply {
            putBoolean(UPDATE_TASK, true)
            putParcelable(TASK_ENTITY, task)
        }

        bottomSheet.show(childFragmentManager, "")
    }

    private fun deleteTask(task: Task) {
        tasksViewModel.deleteTask(task)
    }

}