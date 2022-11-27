package com.example.firstexampleapp.model.question

data class QuestionState(
    val idQuestion: String = "",
    val question: String = "",
    val answer: String = "",
    val answered: Boolean = false
)