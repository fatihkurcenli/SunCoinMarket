package com.autumnsun.suncoinmarket.features.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem
import com.autumnsun.suncoinmarket.features.feature_home.domain.use_cases.GetAllCoins
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCoins: GetAllCoins
) : ViewModel() {

    private val _homeModel = MutableStateFlow<PagingData<HomeModelDtoItem>>(PagingData.empty())
    val homeModel: StateFlow<PagingData<HomeModelDtoItem>>
        get() = _homeModel

    private val _startStopBoolean = MutableStateFlow(false)
    val startStopBoolean: StateFlow<Boolean>
        get() = _startStopBoolean


    fun startCollectData() = viewModelScope.launch(Dispatchers.IO) {
        getAllCoins().cachedIn(viewModelScope).collectLatest { pagingData ->
            _homeModel.value = pagingData
        }
        /*while (startStopBoolean.value) {

           // delay(1000L)
        }*/
    }

    fun startLifeCycle() = viewModelScope.launch(Dispatchers.IO) {
        _startStopBoolean.value = true
    }

    fun stopLifeCycle() = viewModelScope.launch(Dispatchers.IO) {
        _startStopBoolean.value = false
    }

}