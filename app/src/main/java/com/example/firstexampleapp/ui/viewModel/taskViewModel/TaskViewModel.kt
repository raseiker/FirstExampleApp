package com.example.firstexampleapp.ui.viewModel.taskViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firstexampleapp.model.response.Response
import com.example.firstexampleapp.model.task.TaskRepo
import com.example.firstexampleapp.model.task.TaskState
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel() : ViewModel() {
    private val taskRepo = TaskRepo()
    private var _tasks: MutableStateFlow<List<TaskState>> = MutableStateFlow(mutableListOf())
    val tasks: StateFlow<List<TaskState>> = _tasks.asStateFlow()
    private val _userId = mutableStateOf("")

    init {
//        getAllTask()
//        getAllTaskByUserId()
    }

    private fun taskListToStateFlow(list: List<TaskState>): MutableList<MutableStateFlow<TaskState>> {
        val new: MutableList<MutableStateFlow<TaskState>> = mutableListOf()
        list.forEach { task ->
            new.add(MutableStateFlow(task))
        }
        return new
    }

    fun onCheckedChange(new: Boolean, idTask: String){
        Log.d("taskClicked", idTask)
        updateTask(taskState = _tasks.value.first { it.idTask == idTask }.copy(done = new), userId = _userId.value )
//        _tasks.first { it.value.idTask == idTask }.update { it.copy(isDone = new) }
//        _tasks.value.first { it.value.idTask == idTask }.update { it.copy(isDone = new) }
    }

    fun getTaskCompletedCount() = _tasks.value.count { it.done }//_tasks.count { it.value.isDone }

    //this function retrieve all task from firestore
    fun getAllGeneralTask(userId: String) = viewModelScope.launch {
        taskRepo.getAllTask().collect{ res ->
            when(res) {
                is Response.Error -> Log.d("getTask", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getTask", res.data.size.toString())
                    .also {
                        putAllTaskOnMyCollection(res.data, userId) ; getAllTaskByUserId(userId)
                    }
            }
        }
    }

    //this function retrieve all task from firestore filtered by logged user id
    fun getAllTaskByUserId(userId: String) = viewModelScope.launch {
        taskRepo.getAllTaskByUserId(userId = userId).collect { res ->
            when(res) {
                is Response.Error -> Log.d("getTask", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getTask", res.data.size.toString())
                    .also { _tasks.update { res.data }; _userId.value = userId }
            }
        }
    }

    //this function update isDone field into specific task from firestore
    private fun updateTask(taskState: TaskState, userId: String) = viewModelScope.launch {
        when (val res = taskRepo.updateTask(taskState = taskState, userId = userId)){
            is Response.Error -> Log.d("updateTask", res.error)
            Response.Loading -> TODO()
            is Response.Success -> Log.d("updateTask", res.data.toString())
        }
    }

    //this function copy current general task to task list user
    private fun putAllTaskOnMyCollection(list: List<TaskState>, userId: String) = viewModelScope.launch {
        list.forEach { task ->
            updateTask(taskState = task, userId = userId)
        }
    }


}