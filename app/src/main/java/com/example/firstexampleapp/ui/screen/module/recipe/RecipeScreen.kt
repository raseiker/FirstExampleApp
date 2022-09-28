package com.example.firstexampleapp.ui.screen.module.recipe

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R

import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.MyFab
import com.example.firstexampleapp.ui.utils.MyNormalCard
import com.example.firstexampleapp.ui.utils.MyText
import com.example.firstexampleapp.ui.utils.MyTopApBar

//@Preview(showBackground = true, device = Devices.DEFAULT)
@ExperimentalMaterialApi
@Composable
fun RecipeScreenPreview() {
    FirstExampleAppTheme(
        darkTheme = true
    ) {
        RecipeScreen()
    }
}

@ExperimentalMaterialApi
@Composable
fun RecipeScreen() {
    Scaffold(
        floatingActionButton = {
            MyFab()
        },
        topBar = {
            MyTopApBar(
                title = "Recetas",
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
                text = "Mis recetas",
                isTitle = true,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            repeat(times = 4) {

                //add recipe
                MyNormalCard(
                    title = "Arroz con pollo",
                    subTitle = "19g calorias, 8g de colesterol, 25g de grasas, 14g de proteina" +
                            ", 3g de calorias",
                    icon = R.drawable.ic_baseline_menu_book_24,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}