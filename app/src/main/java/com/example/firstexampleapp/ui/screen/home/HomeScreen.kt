package com.example.firstexampleapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*

//@Preview(showBackground = true, device = Devices.DEFAULT)
@ExperimentalMaterialApi
@Composable
fun HomeScreenPreview() {
    FirstExampleAppTheme(darkTheme = false) {
        HomeScreen()
    }
}

@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            MyBottomBar(
                isSelected = listOf(true, false, false)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {

            //show welcome message
            MyWelcomeMessage(
                userName = "Carmela",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp)
            )

            //show progress pregnancy card
            MyProgressIndicatorCard(
                title = "13 semanas de embarazo",
                subTitle = "1er Trimestre",
                progress = 0.3f,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //weight calculator
            MyNormalCard(
                title = "Calculadora de peso",
                subTitle = "Ingresa tu peso actual",
                icon = R.drawable.ic_weight_scale_solid,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //random article card 1
            MyArticleCard(
                title = "Como me ayudan las vitaminas y minerales",
                subTitle = "Las vitaminas y minerales desarrollan una función muy importante para el desarrollo de todas tus funciones",
                image = R.mipmap.wiegth,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //food calculator
            MyNormalCard(
                title = "Calculadora de alimentos",
                subTitle = "Identifica los alimentos mas apropiados para ti",
                icon = R.drawable.ic_apple_whole_solid,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //random article card 2
            MyArticleCard(
                title = "Como me ayudan las vitaminas y minerales",
                subTitle = "Las vitaminas y minerales desarrollan una función muy importante para el desarrollo de todas tus funciones",
                image = R.mipmap.wiegth,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //add diary
            MyNormalCard(
                title = "Preguntas",
                subTitle = "Crea preguntas y respuestas que te ayudarán a resolver tus dudas",
                icon = R.drawable.ic_circle_question_solid,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //random article card 3
            MyArticleCard(
                title = "Como me ayudan las vitaminas y minerales",
                subTitle = "Las vitaminas y minerales desarrollan una función muy importante para el desarrollo de todas tus funciones",
                image = R.mipmap.wiegth,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            //add task
            MyNormalCard(
                title = "Tareas",
                subTitle = "Crea una lista de tareas a realizar",
                icon = R.drawable.ic_baseline_checklist_24,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}