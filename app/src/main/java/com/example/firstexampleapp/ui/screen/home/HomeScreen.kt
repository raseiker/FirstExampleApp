package com.example.firstexampleapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.ui.utils.*
import com.example.firstexampleapp.ui.viewModel.articleViewModel.ArticleViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@ExperimentalMaterialApi
//@Composable
//fun HomeScreenPreview() {
//    FirstExampleAppTheme(darkTheme = false) {
//        HomeScreen()
//    }
//}

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    articleViewModel: ArticleViewModel,
    userViewModel: UserViewModel,
    onCardActionClicked: (String) -> Unit,
    onCardArticleClicked: (String) -> Unit,
    onItemBottomBarClicked: (String) -> Unit
) {
    val userState by userViewModel.user.collectAsState()

    Scaffold(
        bottomBar = {
            MyBottomBar(
                isSelected = listOf(true, false, false),
                onItemBottomBarClicked = onItemBottomBarClicked
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(state = rememberScrollState())
        ) {
            //show welcome message
            MyWelcomeMessage(
                userName = userState.name,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp)
            )

            //show progress pregnancy card
            MyProgressIndicatorCard(
                title = "${userState.pregnancyWeek} semanas de embarazo",
                trimester = userState.trimester,//was trimestre.type
                progress = userState.pregnancyProgress,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            articleViewModel.getSubArticleList().forEach { article ->
                MyArticleCardTest(
                    title = article.title,
                    subTitle = articleViewModel.getSubtitle(article.body),
                    imagePath = article.imagePath,
                    badges = listOf("ARTÍCULO", article.category.uppercase()),
                    onClick = { onCardArticleClicked(article.idArticle) },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}