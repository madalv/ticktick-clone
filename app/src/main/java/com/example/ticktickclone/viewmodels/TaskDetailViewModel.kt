package com.example.ticktickclone.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ticktickclone.models.CompletionStatus
import com.example.ticktickclone.models.Task
import com.example.ticktickclone.repository.TaskListRepository
import com.example.ticktickclone.repository.TaskRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "TaskDetailViewModel"
const val INVALID_TASK_ID: Long = -1

class TaskDetailViewModel(
    private val repository: TaskRepository,
    private val listRepository: TaskListRepository,
    private val taskId: Long,
    private val listId: Long
) : ViewModel() {
    private val _task = MutableStateFlow<Task?>(null)
    val task = _task.asStateFlow().asLiveData()

    init {
        viewModelScope.launch {
            if (taskId == INVALID_TASK_ID) {
                val list = listRepository.getList(listId)
                _task.value = Task("", CompletionStatus.NOT_MARKED, list.id, "", list)
            } else {
                _task.value = repository.getTask(taskId)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared called")

        // can't use VM scope cause it cancels all coroutines on VM death
        GlobalScope.launch {
            task.value?.let {
                repository.upsert(it)
            }
        }
    }

    fun updateTask(onUpdate: (Task) -> Task) {
        _task.update { oldTask ->
            oldTask?.let { onUpdate(it) }
        }
    }
}

class TaskDetailViewModelFactory(
    private val repository: TaskRepository,
    private val listRepository: TaskListRepository,
    private val taskId: Long,
    private val listId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskDetailViewModel(repository, listRepository, taskId, listId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
