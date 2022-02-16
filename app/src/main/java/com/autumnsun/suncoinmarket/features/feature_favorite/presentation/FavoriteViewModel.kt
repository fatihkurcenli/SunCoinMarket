package com.autumnsun.suncoinmarket.features.feature_favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_favorite.domain.use_cases.GetAllFavoriteUseCase
import com.autumnsun.suncoinmarket.features.feature_search.presentation.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase
) : ViewModel() {

    private val _favoriteState = MutableStateFlow(FavoriteListState())
    val favoriteState: StateFlow<FavoriteListState>
        get() = _favoriteState

    fun getAllFavorites() = viewModelScope.launch(Dispatchers.IO) {
        getAllFavoriteUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _favoriteState.value = favoriteState.value.copy(
                        favoriteCoins = result.data!!, isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _favoriteState.value = favoriteState.value.copy(
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _favoriteState.value = favoriteState.value.copy(
                        isLoading = false, errorMessage = result.message ?: ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}