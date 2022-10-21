package com.example.firstexampleapp.ui.viewModel.taskViewModel

import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.model.task.TaskState
import com.example.firstexampleapp.model.task.taskList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {
    private var _tasks:  MutableList<MutableStateFlow<TaskState>> = getTask()
    val tasks: List<StateFlow<TaskState>> = _tasks.map { it.asStateFlow() }

    private fun getTask(): MutableList<MutableStateFlow<TaskState>> {
        val new: MutableList<MutableStateFlow<TaskState>> = mutableListOf()
        taskList.forEach { task ->
            new.add(MutableStateFlow(task))
        }
        return new
    }

    fun onCheckedChange(new: Boolean, idTask: Int = 1){
        _tasks.first { it.value.idTask == idTask }.update { it.copy(isDone = new) }
    }

    fun getTaskCompletedCount(): Int{
        return _tasks.count { it.value.isDone }
    }

}