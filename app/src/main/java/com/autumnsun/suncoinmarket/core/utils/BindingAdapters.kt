package com.autumnsun.suncoinmarket.core.utils

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.autumnsun.suncoinmarket.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


@BindingAdapter(value = ["imageUrl"])
fun ImageView.bindLoadRoundedImage(
    usersImageUrl: String,
) {
    usersImageUrl.let {
        Glide.with(this.context)
            .load(it)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter(value = ["roundedImage24"])
fun ImageView.roundedImage24(
    imageUrl: String?,
) {
    imageUrl.let {
        Glide.with(this.context)
            .load(imageUrl)
            .transform(CenterInside(), RoundedCorners(24))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter(value = ["resizeImageUrl"])
fun ImageView.resizeImageUrl(
    screenShotUrl: String,
) {
    screenShotUrl.let {
        Glide.with(this.context)
            .load(it)
            .apply(RequestOptions().override(600, 300))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter(value = ["largeImageUrl"])
fun ImageView.largeImageUrl(
    screenShotLargeUrl: String,
) {
    screenShotLargeUrl.let {
        Glide.with(this.context)
            .load(it)
            .apply(RequestOptions().override(600, 300))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter(value = ["intToString"])
fun AppCompatTextView.intToString(
    intValue: Int
) {
    intValue.let {
        this.text = intValue.toString()
    }
}

@BindingAdapter(value = ["doubleToString"])
fun AppCompatTextView.doubleToString(
    doubleValue: Double
) {
    doubleValue.let {
        this.text = "$doubleValue $"
    }
}

@BindingAdapter("setArrow")
fun ImageView.setArrow(
    getCoinData: Double?
) {
    getCoinData?.let {
        this.setBackgroundResource(if (getCoinData > 0) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
    }
}

@BindingAdapter("setArrowBlack")
fun ImageView.setArrowBlack(
    getCoinData: Double?
) {
    getCoinData?.let {
        this.setBackgroundResource(if (getCoinData > 0) R.drawable.ic_arrow_up_black else R.drawable.ic_arrow_down_black)
    }
}


@BindingAdapter(value = ["currentValuePrice"])
fun AppCompatTextView.setCurrentValuePrice(
    currentPrice: Double
) {
    this.text = "$currentPrice $"
}


@BindingAdapter(value = ["currentPriceUpgradeOrDown"])
fun AppCompatTextView.setCurrentPriceUpgradeOrDown(
    currentUpgrade: Double
) {
    this.text = "$currentUpgrade %"
}

@BindingAdapter(value = ["setHtmlDescription"])
fun AppCompatTextView.setHtmlDescription(
    description: String?
) {
    description?.let {
        this.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    } ?: ""
}

@BindingAdapter(value = ["checkNullVisible"])
fun ImageView.checkNullVisible(
    imageUrl: String?,
) {
    imageUrl?.let {
        Glide.with(this.context)
            .load(it)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(CenterInside(), RoundedCorners(24))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}
