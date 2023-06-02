package com.example.ticktickclone

class Task(
    var name: String,
    var status: CompletionStatus
) {
   companion object {


       fun createTasks(): ArrayList<Task> {
           val placeholderTasks = ArrayList<Task>()

           for (i in 0 until  30)
               placeholderTasks += Task("Placeholder task $i", CompletionStatus.NOT_MARKED)

           return placeholderTasks
       }
   }
}