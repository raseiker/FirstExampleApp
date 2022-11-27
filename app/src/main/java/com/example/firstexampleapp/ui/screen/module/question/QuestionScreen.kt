package com.example.firstexampleapp.ui.screen.module.question

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun QuestionScreenPreview() {
//    FirstExampleAppTheme(darkTheme = false) {
//        Scaffold() {
//            MyAlertDialog()
//
//        }
//    }
//}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterialApi
@Composable
fun QuestionScreen(
    questionViewModel: QuestionViewModel,
    onExpandibleCardClicked: () -> Unit,
    onNavigateBack: () -> Unit,
    onInfoClicked: () -> Unit
) {
    val questionListState by questionViewModel.questions.collectAsState()
    val questionState by questionViewModel.question.collectAsState()
    var showAlertDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Preguntas",
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
                text = "Mis preguntas",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            if (showAlertDialog) {
                MyAlertDialog(
                    onValueChange = { answer -> questionViewModel.onValueChange(idQuestion = questionState.idQuestion, newAnswer = answer)},
                    onClearText = { questionViewModel.onClearText(questionState.idQuestion) },
                    onSendClicked = { questionViewModel.updateQuestion(questionState = questionState, userId = null); showAlertDialog = false },
                    questionState = questionState
                ) { showAlertDialog = false }
            }

            questionListState.forEach{ question ->
                //show question item
                MyExpandibleNormalCard(
                    question = question.question,
                    answer = question.answer,
                    onValueChange = {},
                    onClearText = {},
                    onCardClicked = { questionViewModel.getQuestionById(question.idQuestion); showAlertDialog = true },
                    onSendClicked = {},
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}