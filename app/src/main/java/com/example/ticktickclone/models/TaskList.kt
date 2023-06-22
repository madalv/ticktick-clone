package com.example.ticktickclone.models

data class TaskList(
    var name: String,
    val tasks: MutableList<Task> = ArrayList()
)