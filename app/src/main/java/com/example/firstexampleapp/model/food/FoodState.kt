package com.example.firstexampleapp.model.food

data class FoodState(
    val name: String = "",
    val nutritionInfo: List<Double> = listOf()//change int to double
)
