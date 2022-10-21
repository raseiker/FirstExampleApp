package com.example.firstexampleapp.ui.screen.explore

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
import com.example.firstexampleapp.ui.utils.MyArticleItemCard
import com.example.firstexampleapp.ui.utils.MyImageHeader
import com.example.firstexampleapp.ui.utils.MyTopApBar

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun ArticleListScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        ArticleListScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun ArticleListScreen() {
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Bebidas y Comidas",
                navIcon = Icons.Default.ArrowBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {

            //show image header
            MyImageHeader(
                image = R.mipmap.fruits,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            repeat(times = 4) {

                //show article list item
                MyArticleItemCard(
                    title = "Vitaminas y minerales a consumir con mas frecuencia",
                    subTitle = "Las vitaminas y minerales desempeñan una funcion muy importante para el desarrollo",
                    image = R.mipmap.wiegth,
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}