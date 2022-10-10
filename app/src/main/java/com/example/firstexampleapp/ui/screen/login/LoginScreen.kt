package com.example.firstexampleapp.ui.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstexampleapp.model.user.UserVar
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel


//@Preview(showBackground = true, device = Devices.DEFAULT)
//@Composable
//fun LoginScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        LoginScreen()
//    }
//}

@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    onSingInClicked: () -> Unit = {},
    onRegisterClicked: (Int) -> Unit = {}
) {
//    val userState by userViewModel.user.collectAsState()
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {

            //show logo
            MyLogo(
                modifier = Modifier
                    .padding(top = 80.dp, bottom = 30.dp)
                    .align(Alignment.CenterHorizontally)
            )

            //show email texfield
            MyTextFieldForm(
                label = "Correo",
                text = userViewModel._credential[UserVar.Email.type] ?: "",
                onValueChange = { email -> userViewModel.onValueChangeCredential(mapOf(UserVar.Email.type to email)) },
                onClearText = { userViewModel.onClearText(attr = UserVar.Email.type) },
                keyboardType = KeyboardType.Email,
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
            )

            //show password textfield
            MyTextFieldForm(
                label = "Contraseña",
                text = userViewModel._credential[UserVar.Password.type] ?: "",
                onValueChange = { pass -> userViewModel.onValueChangeCredential(mapOf(UserVar.Password.type to pass)) },
                keyboardType = KeyboardType.Password,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show forgot my password...
            MyForgivenPassword(
                onRegisterClick = onRegisterClicked,
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
            )
            //show sign in button
            MyButton(
                text = "Ingresar",
                enabled = userViewModel.isValidCredential(),
                onClick = onSingInClicked,
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 30.dp)
            )

            //show divider
            MyDivider(
                text = "o ingresa con",
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
            )

            //show google button
            MySocialMediaButton(modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp))


        }
    }
}