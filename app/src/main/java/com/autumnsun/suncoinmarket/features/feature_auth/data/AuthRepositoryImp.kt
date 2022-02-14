package com.autumnsun.suncoinmarket.features.feature_auth.data

import android.content.SharedPreferences
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.core.utils.Constants.FIREBASE_COLLECTION_USERS
import com.autumnsun.suncoinmarket.features.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    private var operationSuccessful: Boolean = false

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun loginEmail(email: String, password: String): Resource<Boolean> {
        operationSuccessful = false
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            Timber.d("userInfo", firebaseAuth.currentUser?.uid!!)
            operationSuccessful = false
            Resource.Success(operationSuccessful)
        } catch (e: Exception) {
            operationSuccessful = false
            Resource.Error(e.localizedMessage ?: "Unexpected Error")
        }
    }

    override suspend fun registerEmail(
        email: String,
        password: String,
        userName: String
    ): Resource<Boolean> {
        operationSuccessful = false
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if (operationSuccessful) {
                val userid = firebaseAuth.currentUser?.uid!!
                var token = ""
                firebaseAuth.getAccessToken(true).addOnSuccessListener {
                    sharedPreferences.edit().putString("token", it.token).apply()
                    token = it.token.toString()
                }.await()
                val obj = UserModel(
                    userName = userName,
                    userId = userid,
                    email = email,
                    password = password,
                    token = token
                )
                firebaseDb.collection(FIREBASE_COLLECTION_USERS)
                    .document(userid)
                    .set(obj).addOnSuccessListener {
                    }.await()
                Timber.d("userInfoSuccessful", firebaseAuth.currentUser?.uid!!)
                Resource.Success(operationSuccessful)
            } else {
                Timber.d("userInfoUnsuccessful", firebaseAuth.currentUser?.uid!!)
                Resource.Success(operationSuccessful)
            }
            Timber.d("userInfo", firebaseAuth.currentUser?.uid!!)
            Resource.Success(operationSuccessful)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unexpected Error")
        }
    }
}