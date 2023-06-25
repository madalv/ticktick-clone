package com.example.ticktickclone.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ticktickclone.dao.TaskDao
import com.example.ticktickclone.dao.TaskListDao
import com.example.ticktickclone.models.CompletionStatus
import com.example.ticktickclone.models.Task
import com.example.ticktickclone.models.TaskList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val DB_NAME = "task_db"

@Database(entities = [TaskList::class, Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun taskListDao(): TaskListDao

    private class TaskDataBaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { dbse ->
                scope.launch {
                    populateDb(dbse.taskListDao(), dbse.taskDao())
                }
            }
        }

        suspend fun populateDb(listDao: TaskListDao, taskDao: TaskDao) {
            taskDao.deleteAll()
            listDao.upsertTaskList(TaskList("list1", 1))
            listDao.upsertTaskList(TaskList("list1", 2))
            taskDao.upsertTask(Task("task1_1", CompletionStatus.NOT_MARKED, 1))
            taskDao.upsertTask(Task("task1_2", CompletionStatus.NOT_MARKED, 1))
            Log.i("DB", "Populated db")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(TaskDataBaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}