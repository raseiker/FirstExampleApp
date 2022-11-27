package com.example.firstexampleapp.model.user.repository

import com.example.firstexampleapp.model.response.Response
import com.example.firstexampleapp.model.task.TaskState
import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.model.user.UserVar
import com.example.firstexampleapp.model.weight.WeightState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class UserRepo {
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val USER_COLLECTION = "users"
    private val USER_EMAIL_FIELD = "credentials.email"
    private val USER_PASSWORD_FIELD = "credentials.password"
    private val WEIGHT_RECORD_FIELD = "weightRecord"

    //function to add user
    suspend fun addUser(userState: UserState, authId: String): Response<String> {
        return try {
            val newRef = db.collection(USER_COLLECTION).document(authId)
            newRef.set(userState.copy(idUser = authId)).await()
            Response.Success(data = "newRef.id")
        } catch (e: Exception) {
            Response.Error(error = e.toString())
        }
    }

    //function to get user by credentials
    suspend fun verifyUserCredentials(email: String, password: String): Response<UserState> {
        return try {
            val user = db.collection(USER_COLLECTION)
                .whereEqualTo(USER_EMAIL_FIELD, email.lowercase().trim())
                .get()
                .await()
                .first()
                .toObject(UserState::class.java)
            if (user.credentials[UserVar.Password.type] == password.trim()) Response.Success(data = user)
            else Response.Error(error = "contraseña incorrecta")
        } catch (e: Exception) {
            Response.Error(error = "el correo es incorrecto")
        }
    }

    //function to add weighRecord field to specific user
    suspend fun addWeightRecord(newValue: WeightState, userId: String): Response<Boolean>{
        return try {
            db.collection(USER_COLLECTION).document(userId)
                .update(WEIGHT_RECORD_FIELD, FieldValue.arrayUnion(newValue))
                .await()
            Response.Success(data = true)
        } catch (e: Exception){
            Response.Error(error = e.toString())
        }
    }

    //function to delete weighRecord field to specific user
    suspend fun deleteWeightRecord(deletedValue: WeightState, userId: String): Response<Boolean>{
        return try {
            db.collection(USER_COLLECTION).document(userId)
                .update(WEIGHT_RECORD_FIELD, FieldValue.arrayRemove(deletedValue))
                .await()
            Response.Success(data = true)
        } catch (e: Exception) {
            Response.Error(error = e.toString())
        }
    }

    //function to get user by uid
    suspend fun getUserByUid(authId: String): Response<UserState> {
        return try {
            val user = db.collection(USER_COLLECTION).document(authId)
                .get()
                .await()
                .toObject(UserState::class.java)!!
            Response.Success(data = user)
        } catch (e: Exception) {
            Response.Error(error = e.toString())
        }
    }

    //auth functions
    suspend fun signIn(email: String, password: String): Response<String>{
        return try {
            val user = auth.signInWithEmailAndPassword(email.lowercase().trim(), password.trim()).await()
            Response.Success(user.user!!.uid)
        } catch (e: Exception){
            Response.Error(e.toString())
        }
    }

    //add user to authentication
    suspend fun createUser(email: String, password: String): Response<String> {
        return try{
            val user = auth.createUserWithEmailAndPassword(email.lowercase().trim(), password.trim()).await()
            if (user.user != null) Response.Success(user.user!!.uid)
            else Response.Error("user dont registered")
        } catch (e: Exception){
            Response.Error(e.toString())
        }
    }

    fun getAuthState() = callbackFlow  {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser != null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    fun isUserAuthenticated() = auth.currentUser != null
    fun getCurrentUser() = auth.currentUser
    fun userOut() = auth.signOut()

}

//val users: List<UserState> = listOf(
//    UserState(
//        idUser = "1",
//        name = "Meadow Soprano",
//        age = "23",
//        babySex = "Mujer",
//        lastPeriod = "1/9/2022",
//        credentials = mutableMapOf(UserVar.Email.type to "meadow", UserVar.Password.type to "123456"),
//        imc = mutableMapOf(UserVar.Height.type to "65.0", UserVar.Weight.type to "72.3"),
//        weightRecord = mutableListOf(
//            WeightState(currentDate = "1/10/2022", week = 5, weight = "72.3")
//        )
//    ),
//    UserState(
//        idUser = "2",
//        name = "Adriana Lacerva",
//        age = "25",
//        isFirstChild = true,
//        babySex = "Mujer",
//        lastPeriod = "1/5/2022",
//        credentials = mutableMapOf(UserVar.Email.type to "a", UserVar.Password.type to "1"),
//        imc = mutableMapOf(UserVar.Height.type to "71.5", UserVar.Weight.type to "70.0"),
//        weightRecord = mutableListOf(
//            WeightState(currentDate = "05/10/2022", week = 23, weight = "72.5", changeWeight = 2.5),
//            WeightState(currentDate = "28/09/2022", week = 22, weight = "70.0")
//        )
//    ),
//)