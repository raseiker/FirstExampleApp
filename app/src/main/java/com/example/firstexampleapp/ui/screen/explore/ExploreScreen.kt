package com.example.firstexampleapp.ui.screen.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.model.article.ArticleCat
import com.example.firstexampleapp.ui.utils.MyArticlesCategories
import com.example.firstexampleapp.ui.utils.MyArticlesCategoriesHeader
import com.example.firstexampleapp.ui.utils.MyBottomBar
import com.example.firstexampleapp.ui.utils.MyScreenTitle
import com.example.firstexampleapp.ui.viewModel.articleViewModel.ArticleViewModel
import com.example.firstexampleapp.ui.viewModel.userViewModel.UserViewModel


//@Preview(showBackground = true, device = Devices.DEFAULT)
//@Composable
//fun ExploreScreenPreview() {
//    FirstExampleAppTheme(darkTheme = true) {
//        ExploreScreen()
//    }
//}


@Composable
fun ExploreScreen(
    articleViewModel: ArticleViewModel,
    userViewModel: UserViewModel,
    onItemBottomBarClicked: (String) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onArticleClicked: (Int) -> Unit
) {
    val articleState by articleViewModel.article.collectAsState()
    Scaffold(
        bottomBar = {
            MyBottomBar(
                isSelected = listOf(false, true, false),
                onItemBottomBarClicked = onItemBottomBarClicked
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
                letter = userViewModel.getFirstNameLetter(),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp)
            )

//            //this should by change by data class. Only UI purposes
            val vitaminArticles = articleState.filter { article ->
                article.category == ArticleCat.VitaminandMineral.category
            }

            val drinkArticle = articleState.filter { article ->
                article.category == ArticleCat.DrinkandFood.category
            }

            val recommendedArticle = articleState.filter { article ->
                article.category == ArticleCat.RecomendedFoods.category
            }

            MyArticlesCategoriesHeader(
                onCategoryClicked = { onCategoryClicked(ArticleCat.VitaminandMineral.category) },
                subtitle = ArticleCat.VitaminandMineral.category,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
            //show categories article
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                items(vitaminArticles) { article ->
                    MyArticlesCategories(
                        image = article.image,
                        subtitle = "Para ti",
                        onArticleClicked = { onArticleClicked(article.idArticle) }
                    )
                }
            }

            MyArticlesCategoriesHeader(
                onCategoryClicked = { onCategoryClicked(ArticleCat.DrinkandFood.category) },
                subtitle = ArticleCat.DrinkandFood.category,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
            //show categories article
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                items(drinkArticle) { article ->
                    MyArticlesCategories(
                        image = article.image,
                        subtitle = "Para ti",
                        onArticleClicked = { onArticleClicked(article.idArticle) }
                    )
                }
            }

            MyArticlesCategoriesHeader(
                onCategoryClicked = { onCategoryClicked(ArticleCat.RecomendedFoods.category) },
                subtitle = ArticleCat.RecomendedFoods.category,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
            //show categories article
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                items(recommendedArticle) { article ->
                    MyArticlesCategories(
                        image = article.image,
                        subtitle = "Para ti",
                        onArticleClicked = { onArticleClicked(article.idArticle) }
                    )
                }
            }

        }
    }
}