package com.example.firstexampleapp.ui.screen.module.task

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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.taskViewModel.TaskViewModel
import kotlinx.coroutines.flow.map

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun TaskScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        TaskScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel,
    onNavigateBack: () -> Unit,
    onInfoClicked: () -> Unit
) {
    val taskState by taskViewModel.tasks.collectAsState()
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Tareas",
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
                text = "Mis tareas",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            taskState.forEach { task ->
                //show add task
                MyCheckBoxCard(
                    task = task.task,
                    isDone = task.done,
                    onCheckedChange = { new -> taskViewModel.onCheckedChange(new = new, idTask = task.idTask) },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}