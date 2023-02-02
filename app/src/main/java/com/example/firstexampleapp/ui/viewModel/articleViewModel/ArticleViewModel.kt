package com.example.firstexampleapp.ui.viewModel.articleViewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexampleapp.R
import com.example.firstexampleapp.model.article.ArticleCat
import com.example.firstexampleapp.model.article.ArticleState
import com.example.firstexampleapp.model.article.repository.ArticleRepo
import com.example.firstexampleapp.model.response.Response
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    private val articleRepo = ArticleRepo()
    private val _articles = MutableStateFlow(listOf<ArticleState>())
    val articles: StateFlow<List<ArticleState>> = _articles.asStateFlow()//now stateflow article variable exists

    val cards = mapOf<Int, List<Any>>(
        0 to listOf(
            "Calculadora de Peso",
            "Ingresa tu peso actual",
            R.drawable.ic_weight_scale_solid,
            "weight"
        ),
        1 to listOf(
            "Calculadora de Alimentos",
            "Identifica los alimentos mas apropiados para ti",
            R.drawable.ic_apple_whole_solid,
            "food"
        ),
        2 to listOf(
            "Preguntas",
            "Crea preguntas y respuestas que te ayudarán a resolver tus dudas",
            R.drawable.ic_circle_question_solid,
            "question"
        ),
        3 to listOf(
            "Tareas",
            "Crea una lista de tareas a realizar",
            R.drawable.ic_baseline_checklist_24,
            "task"
        )
    )

    init {
        getAllArticles()
    }

    fun getArticleById(idArticle: String): ArticleState = _articles.value.first{ it.idArticle == idArticle }//received only first element that matches with the condition

    fun getSubtitle(body: String) = body.substring(IntRange(start = 0, endInclusive = 130))

    fun getSubArticleList(list: List<ArticleState>) = list.take(4)//now receives a list parameter

    fun getRecommendedArticles() = _articles.value.filter { it.category == ArticleCat.RecomendedFoods.category }
    fun getDrinkAndFoodArticles() = _articles.value.filter { it.category == ArticleCat.DrinkandFood.category }
    fun getVitaminAndMineralArticles() = _articles.value.filter { it.category == ArticleCat.VitaminandMineral.category }

    //this function retrieve all shuffled task from firestore
    fun getAllArticles() = viewModelScope.launch {//was private
        articleRepo.getAllArticles().collect{ res ->
            when(res) {
                is Response.Error -> Log.d("getArticles", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getArticles", res.data.size.toString()).also { _articles.update { res.data.shuffled() } }
            }
        }
    }
}

