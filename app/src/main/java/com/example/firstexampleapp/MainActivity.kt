package com.example.firstexampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.unit.*
import com.example.firstexampleapp.ui.navigation.SetUpNavHost
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme

//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase


@ExperimentalUnitApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetUpNavHost()
        }
    }
}


