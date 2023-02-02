package com.example.firstexampleapp.model.user

import com.example.firstexampleapp.model.task.TaskState
import com.example.firstexampleapp.model.weight.WeightState

data class UserState(
    val idUser: String = "",//was int
    val name: String = "",
    val age: String = "",
    @field:JvmField
    val isFirstChild: Boolean = false,
    val babySex: String = "",
    val lastPeriod: String? = null,
    val firstPregnancy: String? = null,
    val pregnancyWeek: Int = 1,//was zero
    val trimester: String = Trimester.Third.type,//before was Trimester type
    val pregnancyProgress: Float = 1f,
    val credentials: MutableMap<String, String> = mutableMapOf(),
    val imc: MutableMap<String, String> = mutableMapOf(),//changed to string type in weight value
    val weightRecord: MutableList<WeightState> = mutableListOf(WeightState()),
//    val taskList: MutableList<TaskState> = mutableListOf()//add this parameter
)


sealed class UserVar(val type: String){
    object Email: UserVar(type = "email")
    object Password: UserVar(type = "password")
    object Password2: UserVar(type = "password2")
    object Name: UserVar(type = "name")
    object Age: UserVar(type = "age")
    object Height: UserVar(type = "height")
    object Weight: UserVar(type = "weight")
}

sealed class Trimester(val type: String){
    object First: Trimester(type = "1 Trimestre")
    object Second: Trimester(type = "2 Trimestre")
    object Third: Trimester(type = "3 Trimestre")
}