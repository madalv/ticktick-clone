package com.example.ticktickclone.repository

import com.example.ticktickclone.dao.TaskDao
import com.example.ticktickclone.models.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    fun getTask(task: Task): Flow<Task> = taskDao.getTask(task.title, task.listId)

    suspend fun upsert(task: Task) = taskDao.upsertTask(task)

    suspend fun delete(task: Task) = taskDao.deleteTask(task)
}