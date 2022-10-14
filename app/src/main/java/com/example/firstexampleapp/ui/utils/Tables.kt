package com.example.firstexampleapp.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme

//@Preview(showBackground = true, device = Devices.NEXUS_5X)
//@Composable
//fun MyTablesPreview() {
//    FirstExampleAppTheme() {
//        Scaffold() {
//            Column(modifier = Modifier.padding(paddingValues = it)) {
//                MyHeaderTable(
//                    columnHeaders = listOf("Fecha", "Semana", "Peso", "Cambio"),
//                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
//                )
//                MyBodyTable(
//                    data = listOf(
//                        listOf("03/05/2022", "10", "60.0kg", "0.0kg"),
//                        listOf("13/11/2022", "28", "78.6kg", "18.5kg"),
//                        listOf("03/06/2022", "30", "81.1kg", "3.0kg"),
//                        listOf("10/08/2022", "32", "85.9kg", "5.6kg")
//                    ),
//                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
//                )
//            }
//        }
//    }
//}

@Composable
fun MyHeaderTable(
    columnHeaders: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            columnHeaders.forEach { header ->
                Text(
                    text = header,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(weight = 1f)
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
        )
    }
}

@Composable
fun MyBodyTable(
    data: MutableList<List<String>>? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        data?.forEach { cells ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                cells.forEach { cell ->
                    Text(
                        text = cell,
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Light),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(weight = 1f)
                    )
                }
            }
        }

    }
}