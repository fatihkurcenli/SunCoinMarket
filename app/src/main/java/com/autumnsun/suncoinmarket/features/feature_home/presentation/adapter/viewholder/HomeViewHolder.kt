package com.autumnsun.suncoinmarket.features.feature_home.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.autumnsun.suncoinmarket.databinding.HomeListCoinItemBinding
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartStackingType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement


class HomeViewHolder(
    private val binding: HomeListCoinItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(homeCoin: HomeModelDtoItem, homeModelClick: (homeModel: HomeModelDtoItem) -> Unit) {
        binding.homeCoinModel = homeCoin
        binding.root.setOnClickListener {
            homeModelClick(homeCoin)
        }
        setLineChart(homeCoin)

    }

    private fun setLineChart(homeCoin: HomeModelDtoItem) {
        val aaChartModel = customScatterChartMarkerSymbolContent(homeCoin)
        aaChartModel.chartType(AAChartType.Line)
        binding.chartView.aa_drawChartWithChartModel(aaChartModel)
    }


    private fun customScatterChartMarkerSymbolContent(homeCoin: HomeModelDtoItem): AAChartModel {
        if (homeCoin.sparklineIn7d?.price != null) {
            val element1 = AASeriesElement()
                .data(homeCoin.sparklineIn7d.price.toTypedArray())
                .name(homeCoin.symbol)
            return AAChartModel()
                .chartType(AAChartType.Scatter)
                .colorsTheme(arrayOf("#F9B770", "#ffc069", "#06caf4", "#7dffc0"))
                .animationDuration(1000)
                .yAxisTitle("")
                .yAxisGridLineWidth(0f)
                .stacking(AAChartStackingType.Normal)
                .markerRadius(1f)
                .series(arrayOf(element1))
        } else {
            return AAChartModel()
        }
    }


    companion object {
        fun create(parent: ViewGroup): HomeViewHolder {
            val view = HomeListCoinItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return HomeViewHolder(view)
        }
    }
}