package com.autumnsun.suncoinmarket.features.feature_detail.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel by viewModels<DetailViewModel>()

    private val safeVarargs: DetailFragmentArgs by navArgs()

    override fun initializeUi() {
        super.initializeUi()
        viewModel.getDetailCoin(safeVarargs.coin.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.detailState.collectLatest { state ->
                if (state.isLoading) {
                    binding.apply {
                        mainLayout.isVisible = false
                        loadingProgressBar.customLoadingAnimation.isVisible = true
                    }
                } else {
                    if (state.detailModel != null) {
                        binding.coinDetailModel = state.detailModel
                        binding.apply {
                            mainLayout.isVisible = true
                            loadingProgressBar.customLoadingAnimation.isVisible = false
                        }
                    }
                    if (state.errorMessage.isNotBlank()) {
                        showSnackBar(state.errorMessage)
                    }
                }
            }
        }
    }
}