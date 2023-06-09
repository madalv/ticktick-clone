package com.example.ticktickclone.repository

import com.example.ticktickclone.dao.TaskListDao
import com.example.ticktickclone.models.ListWithTasks
import com.example.ticktickclone.models.TaskList
import kotlinx.coroutines.flow.Flow

class TaskListRepository(private val listDao: TaskListDao) {

    val allLists: Flow<List<ListWithTasks>> = listDao.getAllLists()
    val firstList: Flow<ListWithTasks> = listDao.getFirst()

    suspend fun getListWithTasks(listId: Long): ListWithTasks = listDao.getListWithTasks(listId)

    suspend fun getList(listId: Long): TaskList = listDao.getList(listId)

    suspend fun upsert(list: TaskList) = listDao.upsertTaskList(list)

    suspend fun delete(list: TaskList) = listDao.deleteList(list)
}
