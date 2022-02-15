package com.autumnsun.suncoinmarket.features.feature_detail.presentation

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.core.utils.animateAlpha
import com.autumnsun.suncoinmarket.databinding.FragmentDetailBinding
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.CoinDetail
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
                            //chartView.aa_drawChartWithChartModel(setLineChart(state.detailModel))
                            setLineChart(state.detailModel)
                            //chartView.aa_drawChartWithChartModel(setLineChart(state.detailModel))
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
        super.initializeUi()
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