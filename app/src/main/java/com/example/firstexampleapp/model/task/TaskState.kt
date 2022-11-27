package com.example.firstexampleapp.model.task

data class TaskState(
    val idTask: String = "",//was int
    val task: String = "",
    val done: Boolean = false
)
