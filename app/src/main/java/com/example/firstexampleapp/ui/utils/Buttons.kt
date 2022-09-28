package com.example.firstexampleapp.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme


//@Preview(showBackground = true, device = Devices.NEXUS_5X)
@Composable
fun MuButtonsPreview(){
    FirstExampleAppTheme() {
        Scaffold() {
            Column(modifier = Modifier.padding(paddingValues = it)) {
                MyButton(text = "Agregar porcion", modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp))
                MyButton(text = "Ingresar", modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp))
                MyButton(text = "Agregar cita", modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp))
                MySocialMediaButton(modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp))
            }
        }
    }
}


@Composable
fun MyButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
            //.padding(horizontal = 0.dp)
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun MySocialMediaButton(
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.secondary),
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)//at least 56 height
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_google_solid),
            contentDescription = "",
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
                .padding(end = 10.dp)
        )
        Text(text = "Google")
    }
}

