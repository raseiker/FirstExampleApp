package com.example.firstexampleapp.ui.screen.login

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.navigation.Screen
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel
import com.example.firstexampleapp.ui.viewModel.taskViewModel.TaskViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    userViewModel: UserViewModel,
    taskViewModel: TaskViewModel,
    questionViewModel: QuestionViewModel,
    recipeViewModel: RecipeViewModel,
    onNavigateTo: (destination: String) -> Unit
) {
    Scaffold {
        LaunchedEffect(key1 = true) {
            val des = if (userViewModel.isUserAuth.value) {
                userViewModel.getUserByUid(authId = userViewModel.getCurrentUser()?.uid!!) { userId ->
                    taskViewModel.getAllTaskByUserId(userId)
                    questionViewModel.getAllQuestionByUserId(userId)
                    recipeViewModel.getAllRecipesByUserId(userId)
                }
                Screen.HomeScreen.route
            } else {
                Screen.LoginScreen.route
            }
            onNavigateTo(des)
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.mipmap.logo_splash),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}