package com.example.ticktickclone.models

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithTasks(
    @Embedded val list: TaskList,
    @Relation(
        parentColumn = "id",
        entityColumn = "list_id"
    )
    val tasks: List<Task>
)
