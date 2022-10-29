package com.example.firstexampleapp.ui.screen.module.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.foodViewModel.FoodViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@Composable
//fun FoodScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        FoodScreen()
//    }
//}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodScreen(
    foodViewModel: FoodViewModel
) {
    val foodState by foodViewModel.food.collectAsState()
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Calculadora de alimentos",
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
                text = "Calcular",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            //show textfield food
            MyTextFieldMenu(
                label = "Seleccionar un alimento",
                items = foodViewModel.getFoodListName(),
                text = foodViewModel.textFood.value,
                onValueChange = { food -> foodViewModel.onValueChange(food = food, code = 0) },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MyTextFieldForm(
                label = "Onzas",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search,
                text = foodViewModel.quantity.value,
                onValueChange = { foodViewModel.onValueChange(food = it, code = 1)},
                onClearText = { foodViewModel.onClearText(code = 1) },
                onSearchClicked = { foodViewModel.onSearchFoodByName(foodViewModel.textFood.value)},
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            //show table food
            MyHeaderTable(
                columnHeaders = listOf("Calorías", "Grasa", "Proteína", "Fibra", "Carbos"),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MyBodyTable(
                data = foodViewModel.foodToList(foodState),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                onCellClicked = {}
            )
        }
    }
}