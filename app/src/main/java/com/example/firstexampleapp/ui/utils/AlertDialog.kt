package com.example.firstexampleapp.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.model.question.QuestionState

@Composable
fun MyAlertDialog(
    onValueChange: (String) -> Unit = {},
    onClearText: () -> Unit = {},
    onSendClicked: () -> Unit = {},
    questionState: QuestionState,
    onDismissRequest: () -> Unit,
){
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {},
        title = { MyText(text = questionState.question, isTitle = true) },
        text = {
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldForm(
                label = "Tu respuesta...",
                text = questionState.answer,
                onValueChange = onValueChange,
                onClearText = onClearText,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send,
                onSendClicked = onSendClicked
            )
        },
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
        modifier = Modifier.heightIn(150.dp)
    )
}