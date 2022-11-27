package com.example.firstexampleapp.ui.viewModel.weightViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.model.weight.WeightState

class WeightViewModel: ViewModel() {
    var weight by mutableStateOf("")
        private set

    fun onImcValueChange(newWeight: String){
        weight = newWeight
    }

    fun onClear() {
        weight = ""
    }

    fun weightToList(weightRecord: MutableList<WeightState>): MutableList<List<String>>{
        val weightList = mutableListOf<List<String>>()
        weightRecord.forEach { weight ->
            weightList.add(listOf(weight.currentDate, weight.week.toString(), "${weight.weight} kg", "+${weight.changeWeight} kg"))
        }
        return weightList
    }
}