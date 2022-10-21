package com.example.firstexampleapp.model.recipe.repository

import com.example.firstexampleapp.model.recipe.RecipeState

val listRecipe = listOf(
    RecipeState(
        idRecipe = 1,
        title = "Arroz con Leche",
        mix = listOf(
            "100 grs de arroz intregral",
            "550 ml de leche",
            "1 rama de canela",
            "1/4 de limón",
            "Canela en polvo para decorar",
            "Unas cuantas gotas de escencia de vainilla (opcional)"
        ),
        cookingTime = "50 min",
        quantity = "4 personas",
        description =
        "1- Con la ayuda de un colador, limpiar bien el arroz." +
                "\n2- En una olla, poner agua a calentar i, cuando esté a punto de hervir, añadir el resto de los ingredientes (excepto la canela en polvo)." +
                "\n3- Cocinar a fuego lento 35-40 minutos, removiendo constantemente para que no se enganche." +
                "\n4- Reservar en la nevera un mínimo de 1-2 h antes de consumir." +
                "\n\nFuente:  “Un Estiu Saludable: Receptes per un estiu amb salut” del Colegio de Dietistas-Nutricionistas de Cataluña"
    ),
    RecipeState(
        idRecipe = 2,
        title = "Risotto con tomates asados y cebada",
        mix = listOf(
            "100 grs de arroz intregral",
            "550 ml de leche",
            "1 rama de canela",
            "1/4 de limón",
            "Canela en polvo para decorar",
            "Unas cuantas gotas de escencia de vainilla (opcional)"
        ),
        cookingTime = "50 min",
        quantity = "4 personas",
        description =
        "1- Con la ayuda de un colador, limpiar bien el arroz." +
                "\n2- En una olla, poner agua a calentar i, cuando esté a punto de hervir, añadir el resto de los ingredientes (excepto la canela en polvo)." +
                "\n3- Cocinar a fuego lento 35-40 minutos, removiendo constantemente para que no se enganche." +
                "\n4- Reservar en la nevera un mínimo de 1-2 h antes de consumir." +
                "\n\nFuente:  “Un Estiu Saludable: Receptes per un estiu amb salut” del Colegio de Dietistas-Nutricionistas de Cataluña",
        nutritionInfo = listOf("540kcal", "50g", "0kg", "1g", "18g")
    )
)