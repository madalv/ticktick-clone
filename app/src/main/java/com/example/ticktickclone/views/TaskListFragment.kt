package com.example.ticktickclone.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticktickclone.databinding.TaskListFragmentBinding
import com.example.ticktickclone.models.Task

private const val TAG = "TaskListFragment"

class TaskListFragment : Fragment() {

    private lateinit var binding: TaskListFragmentBinding
    lateinit var tasks: ArrayList<Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskListFragmentBinding.inflate(layoutInflater, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val rvTasks = binding.taskListRv
        tasks = Task.createTasks()
        val adapter = TaskAdapter(tasks)

        rvTasks.adapter = adapter
        rvTasks.layoutManager = LinearLayoutManager(this.context)

        binding.toolbar.setNavigationOnClickListener {
            // todo: open side menu
            Log.i(TAG, "Nav icon clicked")
        }

        return binding.root
    }

}