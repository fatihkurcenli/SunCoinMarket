package com.autumnsun.suncoinmarket.features.feature_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailUiState())
    val detailState: StateFlow<DetailUiState>
        get() = _detailState

    fun getDetailCoin(id: String?) = viewModelScope.launch(Dispatchers.IO) {
        getCoinUseCase(id).onEach { result ->
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
}