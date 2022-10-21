package com.example.firstexampleapp.ui.viewModel.foodViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.model.food.FoodState
import com.example.firstexampleapp.model.food.listFood
import com.example.firstexampleapp.model.weight.WeightState
import kotlinx.coroutines.flow.*

class FoodViewModel : ViewModel() {
    private var _food = MutableStateFlow(FoodState())
    val food: StateFlow<FoodState> = _food.asStateFlow()
    var textFood = mutableStateOf("")
        private set
    var quantity = mutableStateOf("1")
        private set
    var showMsg = mutableStateOf(false)
    var msg =
        "Nada por aquí. Para descubrir los alimentos disponibles por favor consulta el diccionario..."


    private fun getFoods() = listFood

    //show current text on text field and clean it
    fun onValueChange(food: String, code: Int) {
        when (code) {
            0 -> textFood.value = food.lowercase()
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
    fun onSearchFoodByName(food: String) {
        _food.update {
            getFoods().find { it.name == food }
                .also { if (showMsg.value) showMsg.value = false }
                ?: FoodState().also { showMsg.value = true }
        }
    }

    //show the current food state and convert it to mutable string list
    fun foodToList(foodState: FoodState): MutableList<List<String>> {
        val foodList = mutableListOf<List<String>>()
        if (foodState.nutritionInfo.isNotEmpty()) {//changed for foodState variable. before was _food.value
            val number = if (quantity.value == "") 1 else quantity.value.toInt()
            foodList.add(
                listOf(
                    "${foodState.nutritionInfo[0] * number }kcal",
                    "${foodState.nutritionInfo[1] * number }g",
                    "${foodState.nutritionInfo[2] * number }kg",
                    "${foodState.nutritionInfo[3] * number }g",
                    "${foodState.nutritionInfo[4] * number }g"
                )
            )
        }
        return foodList
    }
}
