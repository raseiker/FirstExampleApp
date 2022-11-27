package com.example.firstexampleapp.ui.screen.module.track

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.MyArticleText
import com.example.firstexampleapp.ui.utils.MyProgressIndicatorCard
import com.example.firstexampleapp.ui.utils.MyTopApBar
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel
import com.example.firstexampleapp.ui.viewModel.recipeViewModel.RecipeViewModel
import com.example.firstexampleapp.ui.viewModel.taskViewModel.TaskViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel
import kotlinx.coroutines.flow.StateFlow

//@Preview(showBackground = true)
//@Composable
//fun TaskPreview() {
//    FirstExampleAppTheme() {
//        TrackScreen()
//    }
//}


@OptIn(ExperimentalMaterialApi::class, ExperimentalUnitApi::class)
@Composable
fun TrackScreen(
    taskCompletedCount: Int,
    recipeCompletedCount: Int,
    questionCompletedCount: Int,
    onNavigateBack: () -> Unit,
    onInfoClicked: () -> Unit,
    userName: String
) {
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Seguimiento",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Info,
                onNavigateBack = onNavigateBack,
                onInfoClicked = onInfoClicked
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {
            MyArticleText(
                text = "$userName, te presentamos un resumen de tus logros a lo largo del uso de la app hasta el día de hoy. Te alentamos a que sigas adelante con " +
                        " tus objetivos. Todos los éxitos para ti y tu bebé.",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp)
            )

            MyProgressIndicatorCard(
                title = "Tareas completadas",
                trimester = "$taskCompletedCount/5",
                progress = getProgressFloatOfCompleted(taskCompletedCount),
                tint = getProgressColorOfCompleted(taskCompletedCount),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyProgressIndicatorCard(
                title = "Recetas completadas",
                trimester = "$recipeCompletedCount/5",
                progress = getProgressFloatOfCompleted(recipeCompletedCount),
                tint = getProgressColorOfCompleted(recipeCompletedCount),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
            MyProgressIndicatorCard(
                title = "Preguntas completadas",
                trimester = "$questionCompletedCount/5",
                progress = getProgressFloatOfCompleted(questionCompletedCount),
                tint = getProgressColorOfCompleted(questionCompletedCount),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
    }
}

fun getProgressColorOfCompleted(completed: Int = 0): Color? {
    return when (completed) {
        in 0..2 -> Color.Red
        3 -> Color.Yellow
        in 4..5 -> null
        else -> Color.Unspecified
    }
}

fun getProgressFloatOfCompleted(completed: Int = 0) = (completed / 5.0).toFloat()
