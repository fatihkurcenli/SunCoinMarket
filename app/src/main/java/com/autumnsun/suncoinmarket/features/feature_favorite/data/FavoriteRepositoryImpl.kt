package com.autumnsun.suncoinmarket.features.feature_favorite.data

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.core.utils.Constants.FIREBASE_COLLECTION_USERS
import com.autumnsun.suncoinmarket.features.feature_auth.data.UserModel
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.autumnsun.suncoinmarket.features.feature_favorite.domain.repository.FavoriteRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FavoriteRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore
) : FavoriteRepository {
    override suspend fun getAllFavoriteCoins(): Flow<Resource<List<FavoriteCoinModel>>> =
        callbackFlow {
            send(Resource.Loading())
            firebaseAuth.currentUser?.uid.let {
                val snapshotListener = firebaseDb.collection(FIREBASE_COLLECTION_USERS)
                    .document(it!!)
                    .addSnapshotListener { snapshot, e ->
                        val response = if (snapshot != null) {
                            val userInfo = snapshot.toObject(UserModel::class.java)
                            Resource.Success(userInfo!!.favoriteCoinList)
                        } else {
                            Resource.Error(e?.message ?: e.toString())
                        }
                        trySend(response).isSuccess
                    }
                awaitClose {
                    snapshotListener.remove()
                }
            }
        }
}