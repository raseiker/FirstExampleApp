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
    recipeState: RecipeState,
    onNavigateBack: () -> Unit,
    onInfoClicked: () -> Unit
) {
    val recipeDoneState by recipeViewModel.recipe.collectAsState()
    Scaffold(
        topBar = {
            MyTopApBar(
                navIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Info,
                onNavigateBack = onNavigateBack,
                onInfoClicked = onInfoClicked
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(state = rememberScrollState())
        ) {
            //show image header
            MyImageLoader(
                imagePath = recipeState.photo,
                modifier = Modifier.padding(bottom = 15.dp),
                imageModifier = Modifier.aspectRatio(ratio = 1.8f / 1.5f)
            )

            //show recipe title
            MyText(
                text = recipeState.title,
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //show recipe header
            MyRecipeHeader(
                cookingTime = recipeState.cookingTime,
                difficulty = recipeState.difficulty,
                quantity = recipeState.quantity,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //show recipe mix
            MyRecipeBody(
                title = "Ingredientes:",
                list = recipeState.mix,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )

            //show recipe steps
            MyRecipeBody(
                title = "Procedimiento:",
                text = recipeState.description.replace("LINEBREAK","\n"),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )

            //show nutrition info recipe
            MyText(
                text = "Valor nutricional por persona:",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
            )

            MyHeaderTable(
                columnHeaders = listOf("Calorías", "Grasa", "Proteína", "Fibra", "Carbos"),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            MyBodyTable(
                data = mutableListOf(recipeState.nutritionInfo),
                onCellClicked = {},
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            //show if was gone
            MySwitchOption(
                text = "Realizado",
                isChecked = recipeDoneState,
                onCheckedChange = { newVal -> recipeViewModel.onCheckedChange(recipeState = recipeState, newVal = newVal) },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}