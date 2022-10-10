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
        title = R.string.title_one.toString(),
        body =  R.string.body_one.toString(),
        bookmarked = false,
        image = R.mipmap.fruits,
        category = ArticleCat.VitaminandMineral.category
    ),
    ArticleState(
        idArticle = 2,
        title = R.string.title_two.toString(),
        body =  R.string.body_two.toString(),
        bookmarked = true,
        image = R.mipmap.fruits,
        category = ArticleCat.DrinkandFood.category
    ),
    ArticleState(
        idArticle = 3,
        title = R.string.title_three.toString(),
        body = R.string.body_three.toString(),
        bookmarked = true,
        image = R.mipmap.wiegth,
        category = ArticleCat.RecomendedFoods.category
    ),
    ArticleState(
        idArticle = 4,
        title = R.string.title_four.toString(),
        body = R.string.body_four.toString(),
        bookmarked = false,
        image = R.mipmap.wiegth,
        category = ArticleCat.RecomendedFoods.category
    ),
    ArticleState(
        idArticle = 5,
        title = R.string.title_five.toString(),
        body = R.string.body_five.toString(),
        bookmarked = false,
        image = R.mipmap.fruits,
        category = ArticleCat.VitaminandMineral.category
    )


)