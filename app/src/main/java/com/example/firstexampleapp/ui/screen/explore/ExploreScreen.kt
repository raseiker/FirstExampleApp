package com.example.firstexampleapp.ui.screen.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.MyArticlesCategories
import com.example.firstexampleapp.ui.utils.MyArticlesCategoriesHeader
import com.example.firstexampleapp.ui.utils.MyBottomBar
import com.example.firstexampleapp.ui.utils.MyScreenTitle


//@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun ExploreScreenPreview() {
    FirstExampleAppTheme(darkTheme = true) {
        ExploreScreen()
    }
}


@Composable
fun ExploreScreen() {
    Scaffold(
        bottomBar = {
            MyBottomBar(
                isSelected = listOf(false, true, false)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {

            //show screen title
            MyScreenTitle(
                title = "Explora",
                subTitle = "Todo lo que necesitas saber",
                letter = "C",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp)
            )

            //this should by change by data class. Only UI purposes
            repeat(times = 3) {
                //show categories article title
                MyArticlesCategoriesHeader(
                    subtitle = "Bebidas y Comidas",
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
                )

                //show categories article
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    items(count = 5) {
                        MyArticlesCategories(
                            image = R.mipmap.wiegth,
                            subtitle = "11 articles",
                        )
                    }
                }
            }


        }
    }
}