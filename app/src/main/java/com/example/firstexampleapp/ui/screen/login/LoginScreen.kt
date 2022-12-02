package com.example.firstexampleapp.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.model.user.UserVar
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel
import com.example.firstexampleapp.ui.viewModel.taskViewModel.TaskViewModel
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
    taskViewModel: TaskViewModel,
    questionViewModel: QuestionViewModel,
    recipeViewModel: RecipeViewModel,
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
                text = "Aun no tengo cuenta. Registrarme",
                onRegisterClick = { onRegisterClicked(it); userViewModel.onClearText("all") },
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
            )
            //show sign in button
            MyButton(
                text = "Ingresar",
                enabled = true,//userViewModel.isValidCredential(),
                onClick = { userViewModel.signIn { id ->
                            taskViewModel.getAllTaskByUserId(id)
                            questionViewModel.getAllQuestionByUserId(id)
                            recipeViewModel.getAllRecipesByUserId(id)
                            onSingInClicked()
                        } },
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 30.dp)
            )

            if (userViewModel.msgError.value.length > 1) MyToastText(text = userViewModel.msgError.value)
        }
    }
}

@Composable
fun MyToastText(text: String){
    Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT).show()
}