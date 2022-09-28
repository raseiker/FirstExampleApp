package com.example.firstexampleapp.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*

@Preview(showBackground = true, device = Devices.DEFAULT)
@ExperimentalMaterialApi
@Composable
fun AboutYouScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        AboutYouScreen()
    }
}

@ExperimentalMaterialApi
@Composable
fun AboutYouScreen() {
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Sobre ti...",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = null
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            //show text
            MyText(
                text = "Esta app funciona de forma personalizada, para ello necesitamos" +
                        " algunos datos sobre ti y tu bebé",
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 30.dp)
            )

            //show name texfield
            MyTextFieldForm(
                label = "Nombre",
                keyboardType = KeyboardType.Text,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show name texfield
            MyTextFieldForm(
                label = "Edad",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show child textfield menu
            MyTextFieldMenu(
                label = "Es mi primer hijo",
                items = listOf("Sí", "No"),
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show babysex textfield menu
            MyTextFieldMenu(
                label = "Sexo del bebe",
                items = listOf("Hombre", "Mujer", "Prefiero no decirlo"),
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show next button
            MyButton(
                text = "Siguiente",
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
            )
        }
    }
}