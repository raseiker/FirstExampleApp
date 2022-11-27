package com.example.firstexampleapp.model.task

import com.example.firstexampleapp.model.response.Response
import com.example.firstexampleapp.model.user.UserState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class TaskRepo {
    private val db = Firebase.firestore
    private val TASK_COLLECTION = "tasks"
    private val USER_COLLECTION = "users"
    private val TASK_FIELD = "task"
    private val ISDONE_FIELD = "done"

    //function to add task
    suspend fun addTask(taskState: TaskState, currentUserId: String): Response<String> {
        return try {
            db.collection(USER_COLLECTION).document(currentUserId).collection(TASK_COLLECTION).document(taskState.idTask)
                .set(taskState).await()
//            Log.d("addPro", "document added")
            Response.Success(data = "document added")
        } catch (e: Exception) {
//            Log.w("addPro", "Error", e)
            Response.Error(error = e.toString())
        }
    }

    //function to get all task from firestore
    fun getAllTask(): Flow<Response<List<TaskState>>> {
        return db.collection(TASK_COLLECTION).orderBy(TASK_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(TaskState::class.java)) }
            .catch { Response.Error(it.toString()) }
    }

    //function to get all task from firestore by user id
    fun getAllTaskByUserId(userId: String): Flow<Response<List<TaskState>>> {
        return db.collection(USER_COLLECTION).document(userId).collection(TASK_COLLECTION).orderBy(TASK_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(TaskState::class.java))
            }
            .catch { Response.Error(it.toString()) }
    }

    //function to update isDone field to user task
    suspend fun updateTask(taskState: TaskState, userId: String): Response<Boolean>{
        return try {
            db.collection(USER_COLLECTION).document(userId).collection(TASK_COLLECTION).document(taskState.idTask)
                .set(taskState, SetOptions.merge())
                .await()
            Response.Success(data = true)
        } catch (e: Exception){
            Response.Error(error = e.toString())
        }
    }
}