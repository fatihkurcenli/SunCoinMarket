package com.autumnsun.suncoinmarket.features.feature_auth.data

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
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
            Resource.Success(operationSuccessful)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unexpected Error")
        }
    }

    override suspend fun registerEmail(email: String, password: String): Resource<Boolean> {
        TODO("Not yet implemented")
    }
}