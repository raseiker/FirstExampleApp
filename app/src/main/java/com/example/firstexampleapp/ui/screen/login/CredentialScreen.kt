package com.example.firstexampleapp.ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstexampleapp.model.user.UserVar
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.MyButton
import com.example.firstexampleapp.ui.utils.MyText
import com.example.firstexampleapp.ui.utils.MyTextFieldForm
import com.example.firstexampleapp.ui.utils.MyTopApBar
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel
import com.example.firstexampleapp.ui.viewModel.taskViewModel.TaskViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun CredentialScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        CredentialScreen()
//    }
//}


@ExperimentalMaterialApi
@Composable
fun CredentialScreen(
    userViewModel: UserViewModel,
    taskViewModel: TaskViewModel,
    recipeViewModel: RecipeViewModel,
    questionViewModel: QuestionViewModel,
    onNavigateBack: () -> Unit,
    onDoneClicked: () -> Unit = {}
) {
    val userState by userViewModel.user.collectAsState()
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Correo y contraseña",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = null,
                onNavigateBack = onNavigateBack
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
                text = userViewModel._credential[UserVar.Email.type] ?: "",
                onValueChange = { email -> userViewModel.onValueChangeCredential(mapOf(UserVar.Email.type to email)) },
                onClearText = { userViewModel.onClearText(attr = UserVar.Email.type) },
                keyboardType = KeyboardType.Email,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show password textfield
            MyTextFieldForm(
                label = "Contraseña",
                text = userViewModel._credential[UserVar.Password.type] ?: "",
                onValueChange = { pass -> userViewModel.onValueChangeCredential(mapOf(UserVar.Password.type to pass))},
                keyboardType = KeyboardType.Password,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show repeat password textfield
            MyTextFieldForm(
                label = "Repetir contraseña",
                text = userViewModel._credential[UserVar.Password2.type] ?: "",
                onValueChange = { pass -> userViewModel.onValueChangeCredential(mapOf(UserVar.Password2.type to pass))},
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            //show text
            MyText(
                text = "Nos complace poder acompañarte a lo largo de tu embarazo, ${userState.name}",
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 40.dp)
            )

            //show next button
            MyButton(
                text = "Empezar",
                enabled = userViewModel.isPassWordValid(),
                onClick = {
                    userViewModel.createUser{ userId ->//add to firestore
                    taskViewModel.getAllGeneralTask(userId); questionViewModel.getAllGeneralQuestion(userId); recipeViewModel.getAllGeneralRecipe(userId) }
                    onDoneClicked()
                          },
                modifier = Modifier.padding(horizontal = 25.dp)
            )

        }
    }
}