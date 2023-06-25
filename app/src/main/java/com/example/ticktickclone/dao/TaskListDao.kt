package com.example.ticktickclone.dao

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.ticktickclone.models.ListWithTasks
import com.example.ticktickclone.models.TaskList
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskListDao {
    @Transaction
    @Query("SELECT * FROM tasklist_table")
    fun getAllLists(): Flow<List<ListWithTasks>>

    // todo in case there's multiple lists with the same name?T
    @Transaction
    @Query("SELECT * FROM tasklist_table WHERE title = :listTitle")
    fun getList(listTitle: String): ListWithTasks

    @WorkerThread
    @Transaction
    @Query("SELECT * FROM tasklist_table LIMIT 1")
    fun getFirst(): Flow<ListWithTasks>

    @Upsert
    suspend fun upsertTaskList(list: TaskList)

    @Delete
    suspend fun deleteList(list: TaskList)
}