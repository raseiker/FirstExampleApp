package com.example.firstexampleapp.model.question

data class QuestionState(
    val idQuestion: Int = 0,
    val question: String = "",
    val answer: String = "",
    val isAnswered: Boolean = false
)