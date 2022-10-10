package com.example.firstexampleapp.model.user.repository

import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.model.user.UserVar

val users: List<UserState> = listOf(
    UserState(
        idUser = 1,
        name = "Meadow Soprano",
        age = "23",
        babySex = "Mujer",
        lastPeriod = "1/9/2022",
        credentials = mutableMapOf(UserVar.Email.type to "meadow", UserVar.Password.type to "123456"),
        imc = mutableMapOf(UserVar.Height.type to 65.0, UserVar.Weight.type to 72.3)
    ),
    UserState(
        idUser = 2,
        name = "Adriana Lacerva",
        age = "25",
        isFirstChild = true,
        babySex = "Mujer",
        lastPeriod = "1/5/2022",
        credentials = mutableMapOf(UserVar.Email.type to "adriana", UserVar.Password.type to "123456"),
        imc = mutableMapOf(UserVar.Height.type to 71.5, UserVar.Weight.type to 70.0)
    ),
)