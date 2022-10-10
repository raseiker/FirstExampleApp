package com.example.firstexampleapp.model.user

data class UserState(
    val idUser: Int = 0,
    val name: String = "",
    val age: String = "",
    val isFirstChild: Boolean = false,
    val babySex: String = "",
    val lastPeriod: String? = null,
    val firstPregnancy: String? = null,
    val pregnancyWeek: Int = 0,
    val trimester: Trimester = Trimester.Third,
    val pregnancyProgress: Float = 1f,
    val credentials: MutableMap<String, String> = mutableMapOf(),
    val imc: MutableMap<String, Double> = mutableMapOf()
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