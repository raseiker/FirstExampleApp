package com.example.firstexampleapp.ui.viewModel.recipeViewModel

import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.model.recipe.RecipeState
import com.example.firstexampleapp.model.recipe.repository.listRecipe
import kotlinx.coroutines.flow.*

class RecipeViewModel : ViewModel() {
    private val _recipe: MutableList<MutableStateFlow<RecipeState>> = getRecipes()
    val recipe: List<StateFlow<RecipeState>> = _recipe.map { it.asStateFlow() }

    private fun getRecipes(): MutableList<MutableStateFlow<RecipeState>> {
        val new: MutableList<MutableStateFlow<RecipeState>> = mutableListOf()
        listRecipe.forEach { recipe ->
            new.add(MutableStateFlow(recipe))
        }
        return new
    }

    fun getRecipeById(idRecipe: Int): StateFlow<RecipeState> = _recipe.first { it.value.idRecipe == idRecipe }

    fun onCheckedChange(newVal: Boolean, idRecipe: Int) = _recipe.first { it.value.idRecipe == idRecipe }.update { it.copy(isDone = newVal) }

    fun getRecipeCompletedCount(): Int{
        return _recipe.count { it.value.isDone }
    }
}