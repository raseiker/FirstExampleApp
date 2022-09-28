package com.example.firstexampleapp.ui.screen.module.task

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
fun TaskScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        TaskScreen()
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskScreen() {
    Scaffold(
        floatingActionButton = {
            MyFab()
        },
        topBar = {
            MyTopApBar(
                title = "Tareas",
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
                text = "Mis tareas",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            repeat(times = 2) {
                //show add task
                MyCheckBoxCard(
                    task = "Comprar vitaminas prenatales",
                    isDone = false,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )

                MyCheckBoxCard(
                    task = "Preguntar al nutriologo sobre el mercurio en el pescado",
                    isDone = true,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}