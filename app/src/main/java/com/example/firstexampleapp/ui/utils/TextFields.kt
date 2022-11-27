package com.example.firstexampleapp.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.R


//@Preview(showBackground = true, device = Devices.NEXUS_5X)
//@ExperimentalMaterialApi
//@Composable
//fun MyTextFieldsPreview() {
//    FirstExampleAppTheme() {
//        Scaffold() {
//            Column(modifier = Modifier.padding(paddingValues = it)) {
//                MyTextFieldForm(
//                    label = "Correo",
//                    keyboardType = KeyboardType.Email,
//                    modifier = Modifier.padding(horizontal = 25.dp)
//                )
//                MyTextFieldForm(
//                    label = "Busca un alimento",
//                    keyboardType = KeyboardType.Text,
//                    modifier = Modifier.padding(horizontal = 25.dp)
//                )
//                MyTextFieldForm(
//                    label = "Contraseña",
//                    keyboardType = KeyboardType.Password,
//                    modifier = Modifier.padding(horizontal = 25.dp)
//                )
//                MyTextFieldMenu(
//                    label = "Es mi primer hijo",
//                    items = listOf("Sí", "No"),
//                    modifier = Modifier.padding(horizontal = 25.dp)
//                )
//                MyTextFieldMenu(
//                    label = "Sexo del bebe",
//                    items = listOf("Hombre", "Mujer", "Prefiero no decirlo"),
//                    modifier = Modifier.padding(horizontal = 25.dp)
//                )
//            }
//        }
//    }
//}

@Composable
fun MyTextFieldForm(
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
    text: String = "",
    onValueChange: (String) -> Unit = {},
    onClearText: () -> Unit = {},
    onSendClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {}
) {
    //var text by remember { mutableStateOf("") }
    val isPassword = (keyboardType == KeyboardType.Password)
    var isVisibility by remember { mutableStateOf(value = true) }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            label = {
                Text(
                    text = label,
//                    style = TextStyle(color = Color.Gray.copy(alpha = 0.5f))
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onSend = { onSendClicked() },
                onSearch = { onSearchClicked() }
            ),
            visualTransformation = if (isPassword && isVisibility) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(
                    onClick = { if (isPassword) isVisibility = !isVisibility else onClearText() }
                ) {
                    Icon(
                        painter =
                        if (isPassword) {
                            if (isVisibility) painterResource(id = R.drawable.ic_baseline_visibility_off_24)//on
                            else painterResource(id = R.drawable.ic_baseline_visibility_24)//off
                        } else {
                            painterResource(id = R.drawable.ic_baseline_close_24)
                        },
                        contentDescription = null
                    )
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                cursorColor = MaterialTheme.colors.secondaryVariant,
                focusedLabelColor = MaterialTheme.colors.secondaryVariant
            ),
            shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)//at least 56dp of height
        )
    }
}


@ExperimentalMaterialApi
@Composable
fun MyTextFieldMenu(
    label: String,
    items: List<String>,
    text: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(value = false) }
    //var selectedOptionText by remember { mutableStateOf(value = items[0]) }
// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = text,//selectedOptionText,
            onValueChange = { },
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                cursorColor = MaterialTheme.colors.secondaryVariant,
                focusedLabelColor = MaterialTheme.colors.secondaryVariant
            ),
            shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { selectedItem ->
                DropdownMenuItem(
                    onClick = {
                        //selectedOptionText = selectedItem
                        onValueChange(selectedItem)
                        expanded = false
                    }
                ) {
                    Text(text = selectedItem)
                }
            }
        }
    }
}

@Composable
fun MyTextFieldFormDate(
    label: String,
    textDate: String,
    onClickDate: () -> Unit,
    modifier: Modifier = Modifier
) {
    //var text = textDate
    Column(modifier = modifier) {
        OutlinedTextField(
            value = textDate,//text,
            onValueChange = {},
            label = {
                Text(
                    text = label,
//                    style = TextStyle(color = Color.Gray.copy(alpha = 0.5f))
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            trailingIcon = {
                IconButton(
                    onClick = onClickDate
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                        contentDescription = null
                    )
                }
            },
            singleLine = true,
            readOnly = true,
            shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                cursorColor = MaterialTheme.colors.secondaryVariant,
                focusedLabelColor = MaterialTheme.colors.secondaryVariant
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)//at least 56dp of height
        )
    }
}

@Composable
fun MyTextFieldFormQuestion(
    text: String,
    onValueChange: (String) -> Unit,
    onClearText: () -> Unit,
    onSendClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
//    var textt by remember { mutableStateOf(text) }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            label = { Text(text = "Tu respuesta...") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = { onSendClicked() }
            ),
            trailingIcon = {
                IconButton(
                    onClick = onClearText
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_baseline_close_24),
                        contentDescription = null
                    )
                }
            },
//            singleLine = true,
            readOnly = true,
            maxLines = 2,
            colors = if (MaterialTheme.colors.isLight) {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.secondaryVariant,
                    focusedLabelColor = MaterialTheme.colors.secondaryVariant
                )
            } else {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.secondaryVariant
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)//at least 56dp of height
        )
    }
}

