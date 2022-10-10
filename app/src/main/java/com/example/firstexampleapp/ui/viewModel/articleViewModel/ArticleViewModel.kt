package com.example.firstexampleapp.ui.viewModel.articleViewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.R
import com.example.firstexampleapp.model.article.ArticleState
import com.example.firstexampleapp.model.article.repository.articles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class ArticleViewModel : ViewModel() {
    private val _article = MutableStateFlow(getArticles())
    val article: StateFlow<List<ArticleState>> = _article.asStateFlow()
    var mArticles by mutableStateOf(getArticles())
        private set
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
    var isBottomBarSelected = listOf(false, false, false)
        private set


    fun getArticle(idArticle: Int = 0): ArticleState {
        return article.value.first { it.idArticle == idArticle }//received only first element that matches with the condition
    }

    private fun getArticles(): List<ArticleState> {
        return articles
    }

    fun getSubtitle(body: String): String {
        return body.substring(IntRange(start = 0, endInclusive = 130))
    }

    fun getBadge(badge: String): String{
        return badge.uppercase()
    }
}

