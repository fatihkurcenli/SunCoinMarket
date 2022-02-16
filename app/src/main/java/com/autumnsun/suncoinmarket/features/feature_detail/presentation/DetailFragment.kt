package com.autumnsun.suncoinmarket.features.feature_detail.presentation

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.core.utils.animateAlpha
import com.autumnsun.suncoinmarket.databinding.FragmentDetailBinding
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.CoinDetail
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartStackingType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAMarker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel by viewModels<DetailViewModel>()
    private val safeVarargs: DetailFragmentArgs by navArgs()

    override fun initializeUi() {
        detailStateObserver()
        favoriteStateObserver()
        checkFavoriteAlready()
        super.initializeUi()
    }

    private fun checkFavoriteAlready() {
        safeVarargs.coin.id?.let { viewModel.checkFavoriteState(it) }
        lifecycleScope.launch {
            viewModel.initFavorite.collectLatest {
                if (viewModel.initFavorite.value) {
                    binding.toolBar.favoriteAddButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    binding.toolBar.favoriteAddButton.setBackgroundResource(R.drawable.ic_un_favorite)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBar.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolBar.favoriteAddButton.setOnClickListener {
            viewModel.detailState.value.detailModel?.let {
                val favoriteCoinModel = FavoriteCoinModel(it.id, it.name, it.image.large, it.symbol)
                viewModel.setFavoriteCoin(favoriteCoinModel)
            }
        }
    }

    private fun detailStateObserver() {
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
                            setLineChart(state.detailModel)
                            loadingProgressBar.customLoadingAnimation.isVisible = false
                            mainCard.animateAlpha(View.VISIBLE, 1000L)
                        }
                    }
                    if (state.errorMessage.isNotBlank()) {
                        showSnackBar(state.errorMessage)
                    }
                }
            }
        }
        viewModel.getDetailCoin(safeVarargs.coin.id)
    }

    private fun favoriteStateObserver() {
        lifecycleScope.launch {
            viewModel.favoriteState.collectLatest { state ->
                if (state.isLoading) {
                    binding.toolBar.apply {
                        favoriteAddButton.isVisible = false
                        loadingProgressBar.customLoadingAnimation.isVisible = true
                    }
                } else {
                    if (state.success) {
                        binding.toolBar.apply {
                            if (state.isFavorite) {
                                favoriteAddButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                            } else {
                                favoriteAddButton.setBackgroundResource(R.drawable.ic_un_favorite)
                            }
                            loadingProgressBar.customLoadingAnimation.isVisible = false
                            favoriteAddButton.animateAlpha(View.VISIBLE, 1000L)
                        }
                    }
                    if (state.failedMessage.isNotBlank()) {
                        showSnackBar(state.failedMessage)
                        binding.toolBar.loadingProgressBar.customLoadingAnimation.isVisible = false
                        binding.toolBar.favoriteAddButton.isVisible = true
                        binding.toolBar.apply {
                            loadingProgressBar.customLoadingAnimation.isVisible = false
                            favoriteAddButton.isVisible = true
                            favoriteAddButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                        }
                    }
                }
            }
        }
    }

    private fun setLineChart(detailModel: CoinDetail) {
        val aaChartModel = customScatterChartMarkerSymbolContent(detailModel)
        aaChartModel.chartType(AAChartType.Line)
        binding.chartView.aa_drawChartWithChartModel(aaChartModel)
    }

    private fun customScatterChartMarkerSymbolContent(detailModel: CoinDetail): AAChartModel {
        if (detailModel.marketData.sparkline.price != null) {
            val element1 = AASeriesElement()
                .name(detailModel.symbol)
                .data(detailModel.marketData.sparkline.price.toTypedArray())
                .marker(AAMarker().symbol("https://www.highcharts.com/samples/graphics/sun.png"))
            return AAChartModel()
                .chartType(AAChartType.Scatter)
                .title(detailModel.name)
                .backgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.all_background_color
                    )
                )
                .colorsTheme(arrayOf("#F9B770", "#ffc069", "#06caf4", "#7dffc0"))
                .animationDuration(1000)
                .yAxisTitle("")
                .subtitle(detailModel.symbol)
                .yAxisGridLineWidth(0f)
                .stacking(AAChartStackingType.Normal)
                .markerRadius(8f)
                .series(arrayOf(element1))
        } else {
            return AAChartModel()
        }
    }
}