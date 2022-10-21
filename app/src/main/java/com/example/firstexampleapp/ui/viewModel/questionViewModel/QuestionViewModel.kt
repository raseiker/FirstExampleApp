package com.example.firstexampleapp.ui.viewModel.questionViewModel

import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.model.question.QuestionState
import com.example.firstexampleapp.model.question.repository.listQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuestionViewModel : ViewModel() {
    private val _question: MutableList<MutableStateFlow<QuestionState>> = getQuestions()
    val question: List<StateFlow<QuestionState>> = _question.map { it.asStateFlow() }

    fun onValueChange(idQuestion: Int, newAnswer: String) {
        _question.first { it.value.idQuestion == idQuestion }.update { it.copy(answer = newAnswer, isAnswered = newAnswer.isNotBlank()) }
    }

    fun onClearText(idQuestion: Int) {
        _question.first { it.value.idQuestion == idQuestion }.update { it.copy(answer = "", isAnswered = false) }
    }


    private fun getQuestions(): MutableList<MutableStateFlow<QuestionState>> {
        val new: MutableList<MutableStateFlow<QuestionState>> = mutableListOf()
        listQuestions.forEach { question ->
            new.add(MutableStateFlow(question))
        }
        return new
    }

    fun getQuestionCompletedCount() = _question.count { it.value.isAnswered }
}
