package com.example.ticktickclone

import android.app.Application
import com.example.ticktickclone.db.TaskDatabase
import com.example.ticktickclone.repository.TaskListRepository
import com.example.ticktickclone.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class TickTickCloneApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { TaskDatabase.getDatabase(this, applicationScope) }
    val listRepository by lazy { TaskListRepository(database.taskListDao()) }
    val taskRepository by lazy { TaskRepository(database.taskDao()) }
}
