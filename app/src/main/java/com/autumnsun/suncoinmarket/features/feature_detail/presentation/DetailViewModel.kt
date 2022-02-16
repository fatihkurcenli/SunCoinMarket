package com.autumnsun.suncoinmarket.features.feature_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases.DetailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailUiState())
    val detailState: StateFlow<DetailUiState>
        get() = _detailState

    private val _favoriteState = MutableStateFlow(FavoriteUiState())
    val favoriteState: StateFlow<FavoriteUiState>
        get() = _favoriteState

    private val _initFavorite = MutableStateFlow(false)
    val initFavorite: StateFlow<Boolean>
        get() = _initFavorite

    init {
        val getId = savedStateHandle.get("id") ?: ""
        if (getId.isNotBlank()) {
            getDetailCoin(savedStateHandle.get("id"))
        }
    }

    fun getDetailCoin(id: String?) = viewModelScope.launch(Dispatchers.IO) {
        savedStateHandle.set("id", id!!)
        detailUseCases.getCoinUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _detailState.value = detailState.value.copy(
                        detailModel = result.data!!, isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _detailState.value = detailState.value.copy(
                        isLoading = true,
                    )
                }
                is Resource.Error -> {
                    _detailState.value = detailState.value.copy(
                        isLoading = false,
                        errorMessage = result.message!!
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setFavoriteCoin(favoriteModel: FavoriteCoinModel) = viewModelScope.launch(Dispatchers.IO) {
        detailUseCases.favoriteUseCase(favoriteModel).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _favoriteState.value = favoriteState.value.copy(
                        isFavorite = result.data ?: false,
                        success = true,
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _favoriteState.value = favoriteState.value.copy(
                        isLoading = true,
                    )
                }
                is Resource.Error -> {
                    _favoriteState.value = favoriteState.value.copy(
                        isLoading = false,
                        isFavorite = result.data ?: false,
                        failedMessage = result.message!!
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkFavoriteState(id: String) = viewModelScope.launch {
        detailUseCases.isFavoriteUseCase(id).collectLatest {
            _initFavorite.value = it
        }
    }

}