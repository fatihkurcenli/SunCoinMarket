package com.autumnsun.suncoinmarket.features.feature_home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.databinding.FragmentHomeBinding
import com.autumnsun.suncoinmarket.features.feature_home.presentation.adapter.CoinAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var coinAdapter: CoinAdapter

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinAdapter = CoinAdapter { coinData ->
            /* val navigation =
                 HomeFragmentDirections.actionHomeFragmentToAppsDetailFragment(appsData)
             findNavController().navigate(navigation)*/
        }

        binding.recyclerview.apply {
            setHasFixedSize(true)
            itemAnimator = null
            adapter = coinAdapter
        }

        lifecycleScope.launch {
            viewModel.homeModel.collectLatest { pagingData ->
                /*binding.notFound.isVisible = false
                binding.appsRecyclerview.isVisible = true*/
                coinAdapter.submitData(pagingData)
            }
        }
        viewModel.startCollectData()
    }
}