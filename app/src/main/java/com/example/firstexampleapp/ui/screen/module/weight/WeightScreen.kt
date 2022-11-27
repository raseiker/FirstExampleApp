package com.example.firstexampleapp.ui.screen.module.weight

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import com.example.firstexampleapp.ui.viewModel.weightViewModel.WeightViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@Composable
//fun WeightScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        WeightScreen()
//    }
//}

@Composable
fun WeightScreen(
    weightViewModel: WeightViewModel,
    userViewModel: UserViewModel,
    onNavigateBack: () -> Unit,
    onInfoClicked: () -> Unit
) {
    val userState by userViewModel.user.collectAsState()
    val keyboard = LocalFocusManager.current
    var isTextShowed by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            MyFab(onClick = { isTextShowed = true})
        },
        topBar = {
            MyTopApBar(
                title = "Mi Peso",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Info,
                onNavigateBack = onNavigateBack,
                onInfoClicked = onInfoClicked
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

            if (isTextShowed) {
                //show weight texfield
                MyTextFieldForm(
                    label = "Ingresa tu nuevo peso (kg)",
                    text = weightViewModel.weight,
                    onValueChange = { weight -> weightViewModel.onImcValueChange(weight) },
                    onClearText = { weightViewModel.onClear() },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Send,
                    onSendClicked = {
                        userViewModel.addWeightRecord(weightViewModel.weight)
                        weightViewModel.onClear()
                        keyboard.clearFocus()
                    },
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 40.dp).focusTarget()
                )
            }

            //show table food
            MyHeaderTable(
                columnHeaders = listOf("Fecha", "Semana", "Peso", "Cambio"),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyBodyTable(
                data = weightViewModel.weightToList(userState.weightRecord),
                onCellClicked = { position -> userViewModel.deleteWeightRecord(position) },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}