package com.antoniocostadossantos.todoapp.ui.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.antoniocostadossantos.todoapp.R
import com.antoniocostadossantos.todoapp.databinding.TodoItemLayoutBinding
import com.antoniocostadossantos.todoapp.model.Task

class TasksAdapter(
    val checkTask: (Task) -> Unit,
    val updateTask: (Task) -> Unit,
    val deleteTask: (Task) -> Unit,
) : RecyclerView.Adapter<TasksAdapter.ToDoViewHolder>() {

    private var taskItems = mutableListOf<Task>()

    fun setItems(items: List<Task>) {
        taskItems.clear()
        taskItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder.create(parent)
    }

    override fun getItemCount() = taskItems.size

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(taskItems[position])

        holder.checked.setOnClickListener { checkTask(taskItems[position]) }
        holder.itemView.setOnLongClickListener {

            val popMenu = PopupMenu(holder.itemView.context, it)
            popMenu.inflate(R.menu.menu_task_options)

            popMenu.setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.update_task -> {
                        updateTask(taskItems[position])
                        true
                    }

                    R.id.delete_task -> {
                        deleteTask(taskItems[position])
                        true
                    }

                    else -> true
                }
            }
            popMenu.gravity = Gravity.END
            popMenu.show()
            true
        }
    }

    class ToDoViewHolder(todoItemLayoutBinding: TodoItemLayoutBinding) :
        RecyclerView.ViewHolder(todoItemLayoutBinding.root) {

        val title = todoItemLayoutBinding.title
        val checked = todoItemLayoutBinding.checkbox

        fun bind(task: Task) {
            title.text = task.title
            checked.isChecked = task.checked
        }

        companion object {
            fun create(parent: ViewGroup): ToDoViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = TodoItemLayoutBinding.inflate(inflater, parent, false)
                return ToDoViewHolder(itemBinding)
            }
        }

    }
}