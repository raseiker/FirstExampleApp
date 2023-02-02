package com.example.firstexampleapp.ui.viewModel.foodViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexampleapp.model.food.FoodRepo
import com.example.firstexampleapp.model.food.FoodState
import com.example.firstexampleapp.model.response.Response
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val foodRepo = FoodRepo()
    private var _food = MutableStateFlow(FoodState())
    val food: StateFlow<FoodState> = _food.asStateFlow()
    var textFood = mutableStateOf("")
        private set
    var quantity = mutableStateOf("1")
        private set
    private var listFood = listOf<FoodState>()

    init { getAllFood() }

    //show current text on text field and clean it
    fun onValueChange(food: String, code: Int) {
        when (code) {
            0 -> textFood.value = food
            1 -> quantity.value = food.filterNot { it.toString() == "." }
        }
    }

    fun onClearText(code: Int) {
        when (code) {
            0 -> textFood.value = ""
            1 -> quantity.value = "1"
        }
    }

    //search for new foods and update the food state
    fun onSearchFoodByName(food: String) = _food.update { listFood.find { it.name == food } ?: FoodState() }


    //show the current food state and convert it to mutable string list
    fun foodToList(foodState: FoodState): MutableList<List<String>> {
        val foodList = mutableListOf<List<String>>()
        if (foodState.nutritionInfo.isNotEmpty()) {//changed for foodState variable. before was _food.value
            val number = if (quantity.value == "") 1 else quantity.value.toInt()
            foodList.add(
                listOf(
                    String.format("%.1f", foodState.nutritionInfo[0] * number),
                    "${String.format("%.1f", foodState.nutritionInfo[1] * number) }g",
                    "${String.format("%.1f", foodState.nutritionInfo[2] * number) }kg",
                    "${String.format("%.1f", foodState.nutritionInfo[3] * number) }g",
                    "${String.format("%.1f", foodState.nutritionInfo[4] * number) }g"
                )
            )
        }
        return foodList
    }

    fun getFoodListName() = listFood.map { foodState -> foodState.name }

    //this function retrieve all foods from firestore
    fun getAllFood() = viewModelScope.launch {//was private
        foodRepo.getAllFood().collect{ res ->
            when(res) {
                is Response.Error -> Log.d("getFood", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getFood", res.data.size.toString()).also { listFood = res.data }
            }
        }
    }
}
