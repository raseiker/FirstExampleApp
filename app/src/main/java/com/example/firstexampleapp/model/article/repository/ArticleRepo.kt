package com.example.firstexampleapp.model.article.repository

import com.example.firstexampleapp.model.article.ArticleState
import com.example.firstexampleapp.model.response.Response
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ArticleRepo {
    private val db = Firebase.firestore
    private val ARTICLE_COLLECTION = "articles"
    private val USER_COLLECTION = "users"
    private val TITLE_FIELD = "title"
    private val ISDONE_FIELD = "done"

    //function to get all articles from firestore
    fun getAllArticles(): Flow<Response<List<ArticleState>>> {
        return db.collection(ARTICLE_COLLECTION).orderBy(TITLE_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(ArticleState::class.java)) }
            .catch { Response.Error(it.toString()) }
    }
}