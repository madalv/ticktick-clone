package com.example.ticktickclone.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ticktickclone.models.ListWithTasks
import com.example.ticktickclone.repository.TaskListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "TaskListViewModel"

class TaskListViewModel(
    repository: TaskListRepository
) : ViewModel() {

    val allLists: LiveData<List<ListWithTasks>> = repository.allLists.asLiveData()

    private val _selectedList: MutableStateFlow<ListWithTasks?> = MutableStateFlow(null)
    val selectedList = _selectedList.asStateFlow().asLiveData()

    init {
        viewModelScope.launch {
            // wait for the db to be populated then set the first list as selected for now
            repository.firstList.collect { listWithTasks ->
                _selectedList.value = listWithTasks
                Log.i(TAG, "Task List VM initialized ${_selectedList.value}")
            }
        }

    }
}

class TaskListViewModelFactory(private val repository: TaskListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

