package com.example.firstexampleapp.ui.screen.module.question

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*

//@Preview(showBackground = true, device = Devices.DEFAULT)
@ExperimentalMaterialApi
@Composable
fun QuestionScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        QuestionScreen()
    }
}

@ExperimentalMaterialApi
@Composable
fun QuestionScreen() {
    Scaffold(
        floatingActionButton = {
            MyFab()
        },
        topBar = {
            MyTopApBar(
                title = "Preguntas",
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
                text = "Mis preguntas",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            repeat(times = 2) {

                //show question item
                MyExpandibleNormalCard(
                    question = "No estoy ganando nada de peso. ¿Deberia cambiar mi dieta?",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
                MyExpandibleNormalCard(
                    question = "¿Cuánto peso debería ganar?",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}