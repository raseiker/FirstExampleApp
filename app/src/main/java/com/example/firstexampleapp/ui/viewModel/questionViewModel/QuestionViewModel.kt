package com.example.firstexampleapp.ui.viewModel.questionViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexampleapp.model.question.QuestionState
import com.example.firstexampleapp.model.question.repository.QuestionRepo
import com.example.firstexampleapp.model.response.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionViewModel : ViewModel() {
    private val questionRepo = QuestionRepo()
    private val _questions: MutableStateFlow<List<QuestionState>> = MutableStateFlow(mutableListOf())
    val questions: StateFlow<List<QuestionState>> = _questions.asStateFlow()
    private var _question = MutableStateFlow(QuestionState())
    val question = _question.asStateFlow()
    private val _userId = mutableStateOf("")

    fun onValueChange(idQuestion: String, newAnswer: String) {
//        _question.first { it.value.idQuestion == idQuestion }.update { it.copy(answer = newAnswer, answered = newAnswer.isNotBlank()) }
        _question.update { it.copy(answer = newAnswer, answered = newAnswer.isNotBlank()) }
    }

    fun onClearText(idQuestion: String) {
//        _question.first { it.value.idQuestion == idQuestion }.update { it.copy(answer = "", answered = false) }
        _question.update { it.copy(answer = "", answered = false) }
    }

    fun getQuestionById(idQuestion: String) = _question.update { _questions.value.first { it.idQuestion == idQuestion } }

    fun getQuestionCompletedCount() = _questions.value.count { it.answered }

    //this function retrieve all question from firestore
    fun getAllGeneralQuestion(userId: String) = viewModelScope.launch {
        questionRepo.getAllQuestion().collect{ res ->
            when(res) {
                is Response.Error -> Log.d("getQuestion", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getQuestion", res.data.size.toString())
                    .also {
                        putAllQuestionOnMyCollection(res.data, userId) ; getAllQuestionByUserId(userId)
                    }
            }
        }
    }

    //this function retrieve all question by specific user into firestore
    fun getAllQuestionByUserId(userId: String) = viewModelScope.launch {
        questionRepo.getAllQuestionByUserId(userId = userId).collect{ res ->
            when (res) {
                is Response.Error -> Log.d("getQuestion", res.error)
                Response.Loading -> TODO()
                is Response.Success -> Log.d("getQuestion", res.data.size.toString()).also { _questions.update { res.data }; _userId.value = userId }
            }
        }
    }

    //this function update certain field into firestore
    fun updateQuestion(questionState: QuestionState, userId: String?) = viewModelScope.launch {
        when (val res = questionRepo.updateQuestion(questionState = questionState, userId = userId ?: _userId.value)) {
            is Response.Error -> Log.d("updateQuestion", res.error)
            Response.Loading -> TODO()
            is Response.Success -> Log.d("updateQuestion", res.data.toString())
        }
    }

    //copy the question general list to user question collection
    private fun putAllQuestionOnMyCollection(list: List<QuestionState>, userId: String) = viewModelScope.launch {
        list.forEach { question ->
            updateQuestion(questionState = question, userId = userId)
        }
    }
}