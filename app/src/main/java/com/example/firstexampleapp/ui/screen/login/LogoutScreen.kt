package com.example.firstexampleapp.ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.model.user.UserVar
import com.example.firstexampleapp.ui.utils.MyButton
import com.example.firstexampleapp.ui.utils.MySwitchOption
import com.example.firstexampleapp.ui.utils.MyText
import com.example.firstexampleapp.ui.utils.MyTopApBar
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import kotlinx.coroutines.flow.StateFlow
//
//@Preview(showBackground = true)
//@Composable
//fun LogoutScreenPreview() {
//    LogoutScreen()
//}


@Composable
fun LogoutScreen(
    userViewModel: UserViewModel,
    isDark: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onLogoutClicked: () -> Unit
) {
    val userState = userViewModel.user.collectAsState()
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Ajustes",
                navIcon = Icons.Default.ArrowBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {
            MyShowInformationOnRow(
                textKey = "Usuario",
                textValue = userState.value.name,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyShowInformationOnRow(
                textKey = "Correo",
                textValue = userState.value.credentials[UserVar.Email.type] ?: "Nothing¿?",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyShowInformationOnRow(
                textKey = "Último periodo",
                textValue = userState.value.lastPeriod ?: "Nothing¿?",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyShowInformationOnRow(
                textKey = "Trimestre actual",
                textValue = userState.value.trimester.type,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MySwitchOption(
                text = "Modo oscuro",
                isChecked = isDark,
                onCheckedChange = onThemeChange,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MyButton(
                text = "Cerrar sesión",
                onClick = {
                    userViewModel.LogoutUser()
                    onLogoutClicked()
                },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )
        }
    }
}

@Composable
fun MyShowInformationOnRow(
    textKey: String,
    textValue: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            MyText(text = textKey, isTitle = true, modifier = Modifier.weight(1f))
            MyText(text = textValue)
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth(), thickness = 1.dp
    )
}