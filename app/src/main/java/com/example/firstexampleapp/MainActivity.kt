package com.example.firstexampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstExampleAppTheme {

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello $name!", color = Color.Green, modifier = Modifier.size(14.dp))
        Text(text = "Hola $name!", modifier = Modifier.padding(top = 25.dp))
    }
}


@Composable
fun DefaultPreview() {
    FirstExampleAppTheme {
        Greeting("IKER")
    }
}

@Preview(showBackground = true, widthDp = 350, heightDp = 600)
@Composable
fun MyApp() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Yellow)
    ) {
        Column(modifier = Modifier.background(color = Color.Green)) {
            Text(
                text = "Hello Iam a Column baby",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red)
                    .padding(15.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Hello Iam a two Column baby",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray)
                    .padding(15.dp),
                textAlign = TextAlign.Center
            )
        }

        Row(modifier = Modifier.background(Color.Blue)) {
            Text(
                text = "Hello Iam a Row baby",
                modifier = Modifier
                    //.fillMaxWidth()
                    .background(color = Color.LightGray)
                    .padding(15.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Hello Iam a two Row baby",
                modifier = Modifier
                    //.fillMaxWidth()
                    .background(color = Color.Cyan)
                    .padding(15.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MyCard(){
    Row(modifier = Modifier.fillMaxWidth()) {
        
    }
}