package com.example.firstexampleapp.ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.MyButton
import com.example.firstexampleapp.ui.utils.MyText
import com.example.firstexampleapp.ui.utils.MyTextFieldForm
import com.example.firstexampleapp.ui.utils.MyTopApBar

//@Preview(showBackground = true, device = Devices.DEFAULT)
@ExperimentalMaterialApi
@Composable
fun CredentialScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        CredentialScreen()
    }
}


@ExperimentalMaterialApi
@Composable
fun CredentialScreen() {
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Correo y contraseña",
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

            Spacer(modifier = Modifier.height(50.dp))

            //show email texfield
            MyTextFieldForm(
                label = "Correo",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show password textfield
            MyTextFieldForm(
                label = "Contraseña",
                keyboardType = KeyboardType.Password,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show repeat password textfield
            MyTextFieldForm(
                label = "Repetir contraseña",
                keyboardType = KeyboardType.Password,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show text
            MyText(
                text = "Nos complace poder acompañarte a lo largo de tu embarazo, Carmela",
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 40.dp)
            )

            //show next button
            MyButton(
                text = "Empezar",
                modifier = Modifier.padding(horizontal = 25.dp)
            )

        }
    }
}