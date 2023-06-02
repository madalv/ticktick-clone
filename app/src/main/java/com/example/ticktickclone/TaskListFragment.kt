package com.example.ticktickclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ticktickclone.databinding.TaskListFragmentBinding

class TaskListFragment: Fragment() {

    private lateinit var binding: TaskListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}