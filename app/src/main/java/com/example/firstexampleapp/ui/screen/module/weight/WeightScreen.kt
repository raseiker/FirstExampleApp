package com.example.firstexampleapp.ui.screen.module.weight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
@Composable
fun WeightScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        WeightScreen()
    }
}

@Composable
fun WeightScreen(
    onDark: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            MyFab(onDark = onDark)
        },
        topBar = {
            MyTopApBar(
                title = "Mi Peso",
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
                text = "Historial",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
            )

            //show table food
            MyHeaderTable(
                columnHeaders = listOf("Fecha", "Semana", "Peso", "Cambio"),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyBodyTable(
                data = listOf(
                    listOf("03/05/2022", "10", "60.0kg", "0.0kg"),
                    listOf("13/11/2022", "28", "78.6kg", "18.5kg"),
                    listOf("03/06/2022", "30", "81.1kg", "3.0kg"),
                    listOf("10/08/2022", "32", "85.9kg", "5.6kg")
                ),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}