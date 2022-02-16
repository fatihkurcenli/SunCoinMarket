package com.autumnsun.suncoinmarket.features.feature_detail.data

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.core.utils.Constants.FIREBASE_COLLECTION_FAVORITE_LIST
import com.autumnsun.suncoinmarket.core.utils.Constants.FIREBASE_COLLECTION_USERS
import com.autumnsun.suncoinmarket.data.local.dao.CoinDao
import com.autumnsun.suncoinmarket.data.local.entity.FavoriteCoinEntity
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_detail.data.model.CoinDetailDto
import com.autumnsun.suncoinmarket.features.feature_detail.data.model.mapper.toFavoriteEntity
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class DetailRepositoryImp @Inject constructor(
    private val apiService: CryptoApi,
    private val firebaseDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val localDb: CoinDao
) : DetailRepository {
    private var operationSuccessful: Boolean = false
    private var errorMessage: String? = null
    private var isFound: Boolean = false

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
        operationSuccessful = false
        errorMessage = null
        isFound = false
        return try {
            val getAllList = localDb.getFavoriteCoins()
            getAllList.forEachIndexed { index, model ->
                if (model.id == favoriteModel.id) {
                    localDb.deleteFavorite(favoriteModel.toFavoriteEntity())
                    isFound = true
                }
            }
            if (!isFound) {
                localDb.insertFavoriteCoin(favoriteModel.toFavoriteEntity())
            }
            val newList = localDb.getFavoriteCoins()
            firebaseAuth.currentUser?.uid.let { userId ->
                firebaseDb.collection(FIREBASE_COLLECTION_USERS).document(userId!!)
                    .update(FIREBASE_COLLECTION_FAVORITE_LIST, newList)
                    .addOnSuccessListener {
                        operationSuccessful = true
                    }.addOnFailureListener {
                        errorMessage = it.message.toString()
                    }.await()
                if (operationSuccessful) {
                    Resource.Success(!isFound)
                } else {
                    if (errorMessage != null) {
                        Resource.Error(errorMessage.toString())
                    } else {
                        Resource.Error("Favorite not added, please try agein later!")
                    }
                }
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun isFavoriteCoin(id: String): Resource<Boolean> {
        return try {
            val getAllList: FavoriteCoinEntity? = localDb.getCoinById(id)
            if (getAllList != null) {
                Resource.Success(true)
            } else {
                Resource.Success(false)
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}