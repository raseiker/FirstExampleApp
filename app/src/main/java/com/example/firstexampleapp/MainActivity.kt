package com.example.firstexampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.compose.rememberNavController
import com.example.firstexampleapp.ui.navigation.SetUpNavHost
import com.example.firstexampleapp.ui.screen.module.ModuleScreen
import com.example.firstexampleapp.ui.screen.module.food.FoodScreen
import com.example.firstexampleapp.ui.screen.module.question.QuestionScreen
import com.example.firstexampleapp.ui.screen.module.weight.WeightScreen
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*

@ExperimentalUnitApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val navController = rememberNavController()
            val isDark by remember { mutableStateOf(false) }

            FirstExampleAppTheme(darkTheme = isDark) {
                SetUpNavHost()
//                HomeScreen()
//                ExploreScreen()
//                ModuleScreen()
//                LoginScreen()
//                AboutYouScreen()
//                CredentialScreen()
//                CustomContentScreen()
//                ArticleListScreen()
//                ArticleScreen()
//                QuestionScreen()
//                RecipeScreen()
//                TaskScreen()
//                FoodScreen(onDark = { isDark = !isDark })
//                WeightScreen(onDark = { isDark = !isDark })
            }
        }
    }
}
