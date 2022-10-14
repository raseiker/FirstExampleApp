package com.example.firstexampleapp.ui.viewModel.questionViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.model.question.QuestionState
import com.example.firstexampleapp.model.question.repository.listQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuestionViewModel : ViewModel() {
    private var _questions1 = MutableStateFlow(listQuestions[0])
    val questions1: StateFlow<QuestionState> = _questions1.asStateFlow()
    private var _questions2 = MutableStateFlow(listQuestions[1])
    val questions2: StateFlow<QuestionState> = _questions2.asStateFlow()
    private var _questions3 = MutableStateFlow(listQuestions[2])
    val questions3: StateFlow<QuestionState> = _questions3.asStateFlow()
    private var _questions4 = MutableStateFlow(listQuestions[3])
    val questions4: StateFlow<QuestionState> = _questions4.asStateFlow()
    private var _questions5 = MutableStateFlow(listQuestions[4])
    val questions5: StateFlow<QuestionState> = _questions5.asStateFlow()

    fun onValueChange(i: Int, newAnswer: String) {
        when(i){
            0 -> _questions1.update { it.copy(answer = newAnswer) }
            1 -> _questions2.update { it.copy(answer = newAnswer) }
            2 -> _questions3.update { it.copy(answer = newAnswer) }
            3 -> _questions4.update { it.copy(answer = newAnswer) }
            4 -> _questions5.update { it.copy(answer = newAnswer) }
        }
    }

    fun onClearText(i: Int) {
        when (i){
            0 -> _questions1.update { it.copy(answer = "") }
            1 -> _questions2.update { it.copy(answer = "") }
            2 -> _questions3.update { it.copy(answer = "") }
            3 -> _questions4.update { it.copy(answer = "") }
            4 -> _questions5.update { it.copy(answer = "") }
        }
    }


    private fun getQuestion() = listQuestions
}
