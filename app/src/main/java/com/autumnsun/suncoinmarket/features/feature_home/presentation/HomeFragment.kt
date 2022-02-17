package com.autumnsun.suncoinmarket.features.feature_home.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.core.utils.Constants.REFRESH_PAGE
import com.autumnsun.suncoinmarket.databinding.FragmentHomeBinding
import com.autumnsun.suncoinmarket.features.feature_home.presentation.adapter.CoinAdapter
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.mapper.toCoin
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var coinAdapter: CoinAdapter
    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startStopBoolean(true)
        createAdapter()

        lifecycleScope.launch {
            viewModel.homeModel.collectLatest { pagingData ->
                coinAdapter.submitData(pagingData)
            }
        }

        refreshData()
    }

    private fun createAdapter() {
        coinAdapter = CoinAdapter { coinData ->
            val navigation =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(coinData.toCoin())
            findNavController().navigate(navigation)
        }

        binding.recyclerview.apply {
            setHasFixedSize(true)
            itemAnimator = null
            adapter = coinAdapter
        }
    }

    private fun refreshData() {
        val refreshPageCount = sharedPreferences.getInt(REFRESH_PAGE, 10)
        this.lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                if (!viewModel.startStopBoolean.value) {
                    break
                }
                viewModel.getAllData()
                delay(refreshPageCount.toLong() * 1000)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.startStopBoolean(false)
    }
}