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
import androidx.compose.ui.unit.*
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
            var isDark by remember {
                mutableStateOf(false)
            }
            FirstExampleAppTheme(darkTheme = isDark) {
                //MyButton(text = "ingresar", modifier = Modifier)

                //MyButtonPreview()
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
                FoodScreen(onDark = { isDark = !isDark })
//                WeightScreen(onDark = { isDark = !isDark })
            }
        }
    }
}

//@Preview(showBackground = true, device = Devices.NEXUS_5X)
@ExperimentalMaterialApi
@Composable
fun MyButtonPreview() {
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Mi peso",
                navIcon = Icons.Default.ArrowBack,
                actionIcon = Icons.Default.Info,
                modifier = Modifier
            )
        },
        floatingActionButton = { MyFab() },
        bottomBar = {
            MyBottomBar(
                isSelected = listOf(true, false, false)
            )
        })
    {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .padding(it)
        ) {

        }
    }
}
