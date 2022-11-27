package com.example.firstexampleapp.ui.viewModel.recipeViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexampleapp.model.question.QuestionState
import com.example.firstexampleapp.model.recipe.RecipeState
import com.example.firstexampleapp.model.recipe.repository.RecipeRepo
import com.example.firstexampleapp.model.response.Response
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val recipeRepo = RecipeRepo()
    private val _recipes: MutableStateFlow<List<RecipeState>> = MutableStateFlow(mutableListOf())
    val recipeS: StateFlow<List<RecipeState>> = _recipes.asStateFlow()
    private val _recipe: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val recipe: StateFlow<Boolean> = _recipe.asStateFlow()
    private val _userId = mutableStateOf("")

    fun getRecipeById(idRecipe: String) =  _recipes.value.first { it.idRecipe == idRecipe }.also { current -> _recipe.update { current.done } }

    fun onCheckedChange(recipeState: RecipeState, newVal: Boolean) {
        _recipe.update { newVal }
            .also { updateRecipe(recipeState = recipeState.copy(done = newVal), userId = _userId.value) }
    }

    fun getRecipeCompletedCount() = _recipes.value.count { it.done }

    //this function retrieve all recipe from firestore
    fun getAllGeneralRecipe(userId: String) = viewModelScope.launch {
        recipeRepo.getAllRecipe().collect{ res ->
            when(res) {
                is Response.Error -> Log.d("getRecipeG", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getRecipeG", res.data.size.toString())
                    .also {
                        putAllRecipeOnMyCollection(res.data, userId) ; getAllRecipesByUserId(userId)
                    }
            }
        }
    }

    //this function retrieve all recipes from firestore filtered by logged user id
    fun getAllRecipesByUserId(userId: String) = viewModelScope.launch {
        recipeRepo.getAllRecipeByUserId(userId = userId).collect { res ->
            when(res) {
                is Response.Error -> Log.d("getRecipe", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getRecipe", res.data.size.toString()).also { _recipes.update { res.data }; _userId.value = userId }
            }
        }
    }

    //this function update isDone field into specific task from firestore
    private fun updateRecipe(recipeState: RecipeState, userId: String) = viewModelScope.launch {
        when (val res = recipeRepo.updateRecipe(recipeState = recipeState, userId = userId)){
            is Response.Error -> Log.d("updateRecipe", res.error)
            Response.Loading -> TODO()
            is Response.Success -> Log.d("updateRecipe", res.data.toString())
        }
    }

    //copy the recipe general list to user recipe collection
    private fun putAllRecipeOnMyCollection(list: List<RecipeState>, userId: String) = viewModelScope.launch {
        list.forEach { recipe ->
            updateRecipe(recipeState = recipe, userId = userId)
        }
    }
}