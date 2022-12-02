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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun AboutYouScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        //AboutYouScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun AboutYouScreen(
    userViewModel: UserViewModel,
    onNextClicked: () -> Unit = {},
    onNavigateBack: () -> Unit,
) {
    val userState by userViewModel.user.collectAsState()
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Sobre ti...",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = null,
                onNavigateBack = onNavigateBack
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
                text = userState.name,
                onValueChange = { text -> userViewModel.onValueChangeName(text) },
                onClearText = { userViewModel.onClearText(attr = "name") },
                keyboardType = KeyboardType.Text,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show age texfield
            MyTextFieldForm(
                label = "Edad",
                keyboardType = KeyboardType.Number,
                text = userState.age,
                onValueChange = { age -> userViewModel.onValueChangeAge(age) },
                onClearText = { userViewModel.onClearText(attr = "age") },
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show child textfield menu
            MyTextFieldMenu(
                label = "Es mi primer hijo",
                items = listOf("No", "Sí"),
                text = userViewModel.isFirstChild.value,
                onValueChange = { isFirst -> userViewModel.onValueChangeFirstChild(isFirst) },
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show babysex textfield menu
            MyTextFieldMenu(
                label = "Sexo del bebe",
                items = listOf("Hombre", "Mujer", "Prefiero no decirlo"),
                text = userState.babySex,
                onValueChange = { babySex -> userViewModel.onValueChangeBabySex(babySex) },
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show next button
            MyButton(
                text = "Siguiente",
                onClick = onNextClicked,
                enabled = userViewModel.isFieldFilled(listOf(userState.name, userState.age, userViewModel.isFirstChild.value, userState.babySex)),
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
            )
        }
    }
}