package com.example.firstexampleapp.model.recipe.repository

import com.example.firstexampleapp.model.recipe.RecipeState
import com.example.firstexampleapp.model.response.Response
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class RecipeRepo {
    private val db = Firebase.firestore
    private val RECIPE_COLLECTION = "recipes"
    private val USER_COLLECTION = "users"
    private val ISDONE_FIELD = "done"
    private val TITLE_FIELD = "title"

    //function to get all recipe from firestore
    fun getAllRecipe(): Flow<Response<List<RecipeState>>> {
        return db.collection(RECIPE_COLLECTION).orderBy(TITLE_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(RecipeState::class.java)) }
            .catch { Response.Error(it.toString()) }
    }

    //function to get all recipes from firestore by user id
    fun getAllRecipeByUserId(userId: String): Flow<Response<List<RecipeState>>> {
        return db.collection(USER_COLLECTION).document(userId).collection(RECIPE_COLLECTION).orderBy(TITLE_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { doc -> Response.Success(data = doc.toObjects(RecipeState::class.java))
            }
            .catch { Response.Error(it.toString()) }
    }

    //function to update isDone field to user task or create recipe object if not exists
    suspend fun updateRecipe(recipeState: RecipeState, userId: String): Response<Boolean> {
        return try {
            db.collection(USER_COLLECTION).document(userId).collection(RECIPE_COLLECTION).document(recipeState.idRecipe)
//                .update(ISDONE_FIELD, newVal)
                .set(recipeState, SetOptions.merge())
                .await()
            Response.Success(data = true)
        } catch (e: Exception){
            Response.Error(error = e.toString())
        }
    }
}