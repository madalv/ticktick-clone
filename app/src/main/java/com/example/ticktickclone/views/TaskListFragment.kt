package com.example.ticktickclone.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticktickclone.TickTickCloneApplication
import com.example.ticktickclone.databinding.TaskListFragmentBinding
import com.example.ticktickclone.viewmodels.INVALID_TASK_ID
import com.example.ticktickclone.viewmodels.TaskListViewModel
import com.example.ticktickclone.viewmodels.TaskListViewModelFactory

private const val TAG = "TaskListFragment"

class TaskListFragment : Fragment() {

    private lateinit var binding: TaskListFragmentBinding

    private val vm: TaskListViewModel by viewModels {
        TaskListViewModelFactory((activity?.application as TickTickCloneApplication).listRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = TaskListFragmentBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val rvTasks = binding.taskListRv
        val adapter = TaskAdapter { taskId ->
            val intent = TaskDetailActivity.newIntent(
                (activity as AppCompatActivity),
                taskId,
                vm.selectedList.value!!.list.id
            )
            startActivity(intent)
        }

        rvTasks.adapter = adapter
        rvTasks.layoutManager = LinearLayoutManager(this.context)

        vm.allLists.observe(viewLifecycleOwner) {
            Log.i(TAG, "Lists updated $it")
        }

        vm.selectedList.observe(viewLifecycleOwner) { list ->
            Log.i(TAG, "Selected list: $list")
            list?.let {
                adapter.submitList(list.tasks)
                binding.toolbar.title = list.list.title
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            // todo: open side menu
            Log.i(TAG, "Nav icon clicked")
        }

        binding.addTaskFloatingBtn.setOnClickListener {
            val intent = TaskDetailActivity.newIntent(
                (activity as AppCompatActivity),
                INVALID_TASK_ID,
                vm.selectedList.value!!.list.id
            )
            startActivity(intent)
            Log.i(TAG, "Add task btn clicked")
        }

        return binding.root
    }
}
