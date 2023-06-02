package com.example.ticktickclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticktickclone.databinding.TaskListFragmentBinding

class TaskListFragment: Fragment() {

    private lateinit var binding: TaskListFragmentBinding
    lateinit var tasks: ArrayList<Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskListFragmentBinding.inflate(layoutInflater, container, false)

        val rvTasks = binding.taskListRv
        tasks = Task.createTasks()
        val adapter = TaskAdapter(tasks)

        rvTasks.adapter = adapter
        rvTasks.layoutManager = LinearLayoutManager(this.context)

        return binding.root
    }

}