package com.example.firstexampleapp.model.question.repository

import com.example.firstexampleapp.model.question.QuestionState
import com.example.firstexampleapp.model.response.Response
import com.example.firstexampleapp.model.task.TaskState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class QuestionRepo {
    private val db = Firebase.firestore
    private val QUESTION_COLLECTION = "questions"
    private val USER_COLLECTION = "users"
    private val ANSWER_FIELD = "answer"
    private val QUESTION_FIELD = "question"
    private val ANSWERED_FIELD = "answered"

    //function to get all question from firestore
    fun getAllQuestion(): Flow<Response<List<QuestionState>>> {
        return db.collection(QUESTION_COLLECTION).orderBy(QUESTION_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(QuestionState::class.java)) }
            .catch { Response.Error(it.toString()) }
    }

    //function to get all questions from firestore by user id
    fun getAllQuestionByUserId(userId: String): Flow<Response<List<QuestionState>>> {
        return db.collection(USER_COLLECTION).document(userId).collection(QUESTION_COLLECTION).orderBy(QUESTION_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(QuestionState::class.java))
            }
            .catch { Response.Error(it.toString()) }
    }

    //function to update answer and answered field to certain question
    suspend fun updateQuestion(questionState: QuestionState, userId: String): Response<Boolean>{
        return try {
            db.collection(USER_COLLECTION).document(userId).collection(QUESTION_COLLECTION).document(questionState.idQuestion)
//                .update(mapOf(ANSWER_FIELD to newValue, ANSWERED_FIELD to newValue.isNotBlank()))
                .set(questionState, SetOptions.merge())
                .await()
            Response.Success(data = true)
        } catch (e: Exception){
            Response.Error(error = e.toString())
        }
    }
}

//val listQuestions = listOf(
//    QuestionState(
//        idQuestion = "1",
//        question = "No estoy ganando nada de peso. ¿Deberia cambiar mi dieta?",
//    ),
//    QuestionState(
//        idQuestion = 2,
//        question = "¿Puedo consumir café en pocas cantidades durante el embarazo?",
//    ),
//    QuestionState(
//        idQuestion = 3,
//        question = "¿De qué manera me afecta la listeriosis?",
//    ),
//    QuestionState(
//        idQuestion = 4,
//        question = "Aproximadamente, ¿Cuántas calorías se necesita para normalizar el perfil glucémico?",
//    ),
//    QuestionState(
//        idQuestion = 5,
//        question = "¿Durante el embarazo es recomendable consumir latas de atún fresco?",
//    )
//)