package com.example.ticktickclone.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticktickclone.R
import com.example.ticktickclone.models.CompletionStatus
import com.example.ticktickclone.models.Task

class TaskAdapter(
    private val tasks: List<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // provide direct reference to views within data item
    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkbox: CheckBox = view.findViewById<CheckBox>(R.id.task_item_checkbox)
        val listName = view.findViewById<TextView>(R.id.task_item_list_name)
    }

    // inflate layout, return view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)

        return TaskViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.checkbox.text = task.name

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            task.status = if (isChecked) CompletionStatus.DONE else CompletionStatus.NOT_MARKED
            Log.i("TaskItem", "Task item ${task.name} has status ${task.status}")
        }
    }
}