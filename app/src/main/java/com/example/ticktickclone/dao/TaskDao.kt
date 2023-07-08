package com.example.ticktickclone.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.ticktickclone.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAllTasks(): Flow<List<Task>>

    // todo in case there's multiple tasks with the same name?
    @Query("SELECT * FROM task_table WHERE title = :taskTitle AND list_id = :listId")
    fun getTask(taskTitle: String, listId: Long): Flow<Task>

    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

}