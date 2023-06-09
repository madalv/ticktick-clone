package com.example.ticktickclone.repository

import com.example.ticktickclone.dao.TaskDao
import com.example.ticktickclone.models.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun getTask(taskId: Long): Task = taskDao.getTask(taskId)

    suspend fun upsert(task: Task) = taskDao.upsertTask(task)

    suspend fun delete(task: Task) = taskDao.deleteTask(task)
}
