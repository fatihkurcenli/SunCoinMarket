package com.autumnsun.suncoinmarket.features.feature_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_search.domain.use_case.SearchQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchQueryUseCase: SearchQueryUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    private val _searchState = MutableStateFlow(CoinListState())
    val searchState: StateFlow<CoinListState>
        get() = _searchState

    fun searchCrypto(searchCryptoName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            searchQueryUseCase(searchCryptoName).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _searchState.value = searchState.value.copy(
                            coins = result.data!!.coins, isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _searchState.value = searchState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _searchState.value = searchState.value.copy(
                            isLoading = false,
                            error = result.message!!
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob = null
    }
}