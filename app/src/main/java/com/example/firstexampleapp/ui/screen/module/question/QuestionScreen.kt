package com.example.firstexampleapp.ui.screen.module.question

import android.util.Log
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
import com.example.firstexampleapp.ui.viewModel.questionViewModel.QuestionViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun QuestionScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        QuestionScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun QuestionScreen(
    questionViewModel: QuestionViewModel
) {
    val questionState1 by questionViewModel.questions1.collectAsState()
    val questionState2 by questionViewModel.questions2.collectAsState()
    val questionState3 by questionViewModel.questions3.collectAsState()
    val questionState4 by questionViewModel.questions4.collectAsState()
    val questionState5 by questionViewModel.questions5.collectAsState()

    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Preguntas",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Info
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

            MyExpandibleNormalCard(
                question = questionState1.question,
                answer = questionState1.answer,
                onValueChange = { answer -> questionViewModel.onValueChange(i = 0, newAnswer = answer)},
                onClearText = { questionViewModel.onClearText(i = 0) },
                onSendClicked = { },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MyExpandibleNormalCard(
                question = questionState2.question,
                answer = questionState2.answer,
                onValueChange = { answer -> questionViewModel.onValueChange(i = 1, newAnswer = answer)},
                onClearText = { questionViewModel.onClearText(i = 1) },
                onSendClicked = { },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MyExpandibleNormalCard(
                question = questionState3.question,
                answer = questionState3.answer,
                onValueChange = { answer -> questionViewModel.onValueChange(i = 2, newAnswer = answer)},
                onClearText = { questionViewModel.onClearText(i = 2) },
                onSendClicked = { },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MyExpandibleNormalCard(
                question = questionState4.question,
                answer = questionState4.answer,
                onValueChange = { answer -> questionViewModel.onValueChange(i = 3, newAnswer = answer)},
                onClearText = { questionViewModel.onClearText(i = 3) },
                onSendClicked = { },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            MyExpandibleNormalCard(
                question = questionState5.question,
                answer = questionState5.answer,
                onValueChange = { answer -> questionViewModel.onValueChange(i = 4, newAnswer = answer)},
                onClearText = { questionViewModel.onClearText(i = 4) },
                onSendClicked = { },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

//            questionState.forEachIndexed{ i, question ->
//                //show question item
//                MyExpandibleNormalCard(
//                    question = question.question,
//                    answer = question.answer,
//                    onValueChange = { answer -> questionViewModel.onValueChange(i = i, newAnswer = answer)},
//                    onClearText = { questionViewModel.onClearText(i) },
//                    onSendClicked = { Log.d("currentAnswer", "${question.answer} at position $i")},
//                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
//                )
//            }
        }
    }
}