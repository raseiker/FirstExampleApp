package com.example.firstexampleapp.model.recipe

data class RecipeState(
    val idRecipe: Int = 0,
    val title: String = "",
    val photo: Int = 0,
    val mix: List<String> = listOf(),
    val cookingTime: String = "0.0",
    val difficulty: Level = Level.Junior,
    val quantity: String = "",
    val description: String = "",
    val nutritionInfo: List<String> = listOf(),
    val isDone: Boolean = false
)

sealed class Level(val type: String){
    object Junior: Level(type = "Principiante")
    object Medium: Level(type = "Medio")
    object Senior: Level(type = "Avanzado")
}
