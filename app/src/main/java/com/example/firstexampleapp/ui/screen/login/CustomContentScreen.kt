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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*

//@Preview(showBackground = true, device = Devices.DEFAULT)
@ExperimentalMaterialApi
@Composable
fun CustomContentScreenPreview() {
    FirstExampleAppTheme(darkTheme = false) {
        CustomContentScreen()
    }
}

@ExperimentalMaterialApi
@Composable
fun CustomContentScreen() {
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Personaliza tu contenido",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = null
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            //show text
            MyText(
                text = "Ingresa tu peso y altura para mostrarte como evoluciona tu peso" +
                        " a lo largo de tu embarazo",
                modifier = Modifier.padding(
                    start = 25.dp,
                    end = 25.dp,
                    top = 40.dp,
                    bottom = 10.dp
                )
            )

            //show height texfield
            MyTextFieldForm(
                label = "Altura (cm)",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show weight texfield
            MyTextFieldForm(
                label = "Peso (kg)",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show text
            MyText(
                text = "Una última cosa para asegurarnos que tu app muestre solo lo que necesitas." +
                        " Por favor ingresa tu último periodo",
                modifier = Modifier.padding(
                    start = 25.dp,
                    end = 25.dp,
                    top = 40.dp,
                    bottom = 10.dp
                )
            )

            //show last period date picker
            MyDatePicker(
                label = "Primer día del último periodo",
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show divider
            MyDivider(
                text = "o",
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
            )

            //show last period date picker
            MyDatePicker(
                label = "Primer día de la concepción",
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