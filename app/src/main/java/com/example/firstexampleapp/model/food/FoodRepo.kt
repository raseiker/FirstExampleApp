package com.example.firstexampleapp.model.food

import android.util.Log
import com.example.firstexampleapp.model.response.Response
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FoodRepo {
    private val db = Firebase.firestore
    private val FOOD_COLLECTION = "foods"
    private val NAME_FIELD = "name"

    //function to get all task from firestore
    fun getAllFood(): Flow<Response<List<FoodState>>> {
        return db.collection(FOOD_COLLECTION).orderBy(NAME_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(FoodState::class.java)) }
            .catch { Response.Error(it.toString()) }
    }
}