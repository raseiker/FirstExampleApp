package com.example.firstexampleapp.model.question

data class QuestionState(
    val question: String = "",
    val answer: String = "",
    val isAnswered: Boolean = answer.isNotBlank()
)
