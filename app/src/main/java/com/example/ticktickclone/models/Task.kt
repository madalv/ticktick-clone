package com.example.ticktickclone.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "task_table",
    foreignKeys = [
        ForeignKey(
            entity = TaskList::class,
            childColumns = ["list_id"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Task(
    @ColumnInfo(name = "task_title")
    var title: String,
    @ColumnInfo(name = "completion_status")
    var status: CompletionStatus,
    @ColumnInfo(name = "list_id", index = true)
    val listId: Long,
    @ColumnInfo(name = "description")
    val description: String,
    @Embedded
    val list: TaskList,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_id")
    val id: Long = 0,
)
