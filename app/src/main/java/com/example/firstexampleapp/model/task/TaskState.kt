package com.example.firstexampleapp.model.task

data class TaskState(
    val idTask: Int = 1,
    val task: String = "",
    val isDone: Boolean = false
)
