package com.example.ticktickclone.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasklist_table")
data class TaskList(
    @ColumnInfo(name = "title")
    var title: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Long = 0
)
