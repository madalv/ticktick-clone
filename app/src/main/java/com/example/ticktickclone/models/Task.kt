package com.example.ticktickclone.models

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "completion_status")
    var status: CompletionStatus,
    @ColumnInfo(name = "list_id", index = true)
    val listId: Long,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Long = 0,
)