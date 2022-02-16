package com.autumnsun.suncoinmarket.features.feature_detail.data

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.core.utils.Constants.FIREBASE_COLLECTION_FAVORITE_LIST
import com.autumnsun.suncoinmarket.core.utils.Constants.FIREBASE_COLLECTION_USERS
import com.autumnsun.suncoinmarket.data.local.dao.CoinDao
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_detail.data.model.CoinDetailDto
import com.autumnsun.suncoinmarket.features.feature_detail.data.model.mapper.toFavoriteEntity
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject


class DetailRepositoryImp @Inject constructor(
    private val apiService: CryptoApi,
    private val firebaseDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val localDb: CoinDao
) : DetailRepository {
    private var operationSuccessful: Boolean = false

    override suspend fun getCoinById(id: String): Resource<CoinDetailDto> {
        operationSuccessful = false
        return try {
            val response = apiService.getCoinByID(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Empty body response")
            } else {
                Resource.Error("Failed request server!")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun favoriteCoin(favoriteModel: FavoriteCoinModel): Resource<Boolean> {
        return try {
            localDb.insertFavoriteCoin(favoriteModel.toFavoriteEntity())
            val updateFavoriteList = localDb.getFavoriteCoins()
            firebaseAuth.currentUser?.uid.let { userId ->
                firebaseDb.collection(FIREBASE_COLLECTION_USERS).document(userId!!)
                    .update(FIREBASE_COLLECTION_FAVORITE_LIST, updateFavoriteList)
                    .addOnSuccessListener {
                        operationSuccessful = true
                    }.addOnFailureListener {
                        Timber.d("Error for update token !")
                    }.await()
                if (operationSuccessful) {
                    Resource.Success(true)
                } else {
                    Resource.Error("Firebase Db Kayıt edilemedi")
                }
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}