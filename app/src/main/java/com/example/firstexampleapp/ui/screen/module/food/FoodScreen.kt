package com.example.firstexampleapp.ui.screen.module.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*

//@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun FoodScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        FoodScreen()
    }
}

@Composable
fun FoodScreen(
    onDark: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            MyFab(onClick = onDark)
        },
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
            MyTextFieldForm(
                label = "Buscar una bebida o alimento",
                keyboardType = KeyboardType.Text,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //show textfield food
            MyTextFieldForm(
                label = "Porciones",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search,
                onSendClicked = {},
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            //show table food
            MyHeaderTable(
                columnHeaders = listOf("Calorías", "Grasa", "Proteína", "Fibra", "Carbos"),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyBodyTable(
//                data = listOf(
//                    listOf("570kcal", "50g", "0kg", "1g", "18g"),
//                    listOf("570kcal", "50g", "0kg", "1g", "18g"),
//                    listOf("570kcal", "50g", "0kg", "1g", "18g")
//                ),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}