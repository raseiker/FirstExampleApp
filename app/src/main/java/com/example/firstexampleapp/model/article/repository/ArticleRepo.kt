package com.example.firstexampleapp.model.article.repository

import android.content.Context
import android.content.res.Resources
import android.content.res.loader.ResourcesProvider
import android.provider.Settings.Global.getString
import androidx.compose.ui.platform.LocalContext
import com.example.firstexampleapp.R
import com.example.firstexampleapp.model.article.ArticleCat
import com.example.firstexampleapp.model.article.ArticleState

val articles: List<ArticleState> = listOf(
    ArticleState(
        idArticle = 1,
        title = R.string.title_one_drink_food.toString(),
        body =  R.string.body_one_drink_food.toString(),
        image = R.mipmap.fish_and_seafood,
        category = ArticleCat.VitaminandMineral.category
    ),
    ArticleState(
        idArticle = 2,
        title = R.string.title_two.toString(),
        body =  R.string.body_two.toString(),
        image = R.mipmap.alcohol,
        category = ArticleCat.DrinkandFood.category
    ),
    ArticleState(
        idArticle = 3,
        title = R.string.title_three.toString(),
        body = R.string.body_three.toString(),
        image = R.mipmap.img_coffee,
        category = ArticleCat.RecomendedFoods.category
    ),
    ArticleState(
        idArticle = 4,
        title = R.string.title_four.toString(),
        body = R.string.body_four.toString(),
        image = R.mipmap.img_oil,
        category = ArticleCat.RecomendedFoods.category
    ),
    ArticleState(
        idArticle = 5,
        title = R.string.title_five.toString(),
        body = R.string.body_five.toString(),
        image = R.mipmap.fruits,
        category = ArticleCat.VitaminandMineral.category
    ),
    ArticleState(
        idArticle = 6,
        title = R.string.title_six.toString(),
        body = R.string.body_six.toString(),
        image = R.mipmap.wiegth,
        category = ArticleCat.RecomendedFoods.category
    ),
    ArticleState(
        idArticle = 7,
        title = R.string.title_seven.toString(),
        body = R.string.body_seven.toString(),
        image = R.mipmap.img_food_recomended,
        category = ArticleCat.RecomendedFoods.category
    ),
    ArticleState(
        idArticle = 8,
        title = R.string.title_eight.toString(),
        body = R.string.body_eight.toString(),
        image = R.mipmap.img_gestational_diabetes,
        category = ArticleCat.VitaminandMineral.category
    ),
    ArticleState(
        idArticle = 9,
        title = R.string.title_nine.toString(),
        body = R.string.body_nine.toString(),
        image = R.mipmap.img_pregnancy_shymptoms,
        category = ArticleCat.DrinkandFood.category
    ),
    ArticleState(
        idArticle = 10,
        title = R.string.title_ten.toString(),
        body = R.string.body_ten.toString(),
        image = R.mipmap.img_listeriosis,
        category = ArticleCat.DrinkandFood.category
    )



)