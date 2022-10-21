package com.example.firstexampleapp.ui.screen.module.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.model.recipe.RecipeState
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel

@Composable
fun RecipeDetailScreen(
    recipeViewModel: RecipeViewModel,
    recipeState: State<RecipeState>
) {
    Scaffold(
        topBar = {
            MyTopApBar(
                navIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Info
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(state = rememberScrollState())
        ) {
            //show recipe title
            MyRecipeTitle(
                title = recipeState.value.title,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp)
            )

            //show recipe header
            MyRecipeHeader(
                cookingTime = recipeState.value.cookingTime,
                difficulty = recipeState.value.difficulty,
                quantity = recipeState.value.quantity,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //show recipe mix
            MyRecipeBody(
                title = "Ingredientes:",
                list = recipeState.value.mix,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )

            //show recipe steps
            MyRecipeBody(
                title = "Procedimiento:",
                text = recipeState.value.description,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )

            //show nutrition info recipe
            MyRecipeBody(
                title = "Valor nutricional:",
                text = "",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )

            MyHeaderTable(
                columnHeaders = listOf("Calorías", "Grasa", "Proteína", "Fibra", "Carbos"),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            MyBodyTable(
                data = mutableListOf(recipeState.value.nutritionInfo),
                onCellClicked = {},
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            //show if was gone
            MySwitchOption(
                text = "Realizado",
                isChecked = recipeState.value.isDone,
                onCheckedChange = { newVal -> recipeViewModel.onCheckedChange(newVal = newVal, idRecipe = recipeState.value.idRecipe) },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}