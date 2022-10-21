package com.example.firstexampleapp.ui.screen.module.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R

import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun RecipeScreenPreview() {
//    FirstExampleAppTheme(
//        darkTheme = true
//    ) {
//        RecipeScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun RecipeScreen(
    recipeViewModel: RecipeViewModel,
    onCardClicked: (Int) -> Unit
) {
    val recipes = recipeViewModel.recipe.map { it.collectAsState() }
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Recetas",
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

            //show title
            MyText(
                text = "Mis recetas saludables",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )



            recipes.forEach { recipe ->
//                MyNormalCard(
//                    title = recipe.value.title,
//                    subTitle = "${recipe.value.cookingTime} · ${recipe.value.quantity} · ${recipe.value.difficulty.type}",
//                    icon = R.drawable.ic_baseline_menu_book_24,
//                    onClick = { onCardClicked(recipe.value.idRecipe) },
//                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
//                )

                MyArticleItemCard(
                    title = recipe.value.title,
                    subTitle = "${recipe.value.cookingTime} · ${recipe.value.quantity} · ${recipe.value.difficulty.type}",
                    image = R.mipmap.fruits,
                    onClick = { onCardClicked(recipe.value.idRecipe) },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}