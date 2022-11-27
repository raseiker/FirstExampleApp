package com.example.firstexampleapp.model.recipe

data class RecipeState(
    val idRecipe: String = "",
    val title: String = "",
    val photo: String = "",
    val mix: List<String> = listOf(),
    val cookingTime: String = "",
    val difficulty: String = Level.Junior.type,
    val quantity: String = "",
    val description: String = "",
    val nutritionInfo: List<String> = listOf(),
    val done: Boolean = false
)

sealed class Level(val type: String){
    object Junior: Level(type = "Principiante")
    object Medium: Level(type = "Medio")
    object Senior: Level(type = "Avanzado")
}
