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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var coinAdapter: CoinAdapter

    private val viewModel by viewModels<HomeViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startStopBoolean(true)
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
                Timber.d(pagingData.toString())
                coinAdapter.submitData(pagingData)
            }
        }

        /* coinAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
             override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                 super.onItemRangeInserted(positionStart, itemCount)
                 binding.recyclerview.smoothScrollToPosition(positionStart)
             }
         })*/

        refreshData()
        //viewModel.getAllData()
    }

    private fun refreshData() {
        /*sharedPreferences = SharedPreferences(requireContext())
        sharedPreferences.getRefreshInterval()?.let {


        }*/
        this.lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                if (!viewModel.startStopBoolean.value) {
                    break
                }
                viewModel.getAllData()
                delay(Integer.parseInt("10").toLong() * 1000)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.startStopBoolean(false)
    }

}