package com.autumnsun.suncoinmarket.core.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
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
    getCoinData: Double
) {
    this.setBackgroundResource(if (getCoinData > 0) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
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
    if (description != null) {
        if (this.visibility == View.GONE) {
            this.isVisible = true
            this.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    } else {
        this.isVisible = false
    }
}

@BindingAdapter(value = ["checkNullVisible"])
fun ImageView.checkNullVisible(
    imageUrl: String?,
) {
    if (imageUrl != null) {
        if (this.visibility == View.GONE) {
            this.isVisible = true
            Glide.with(this.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterInside(), RoundedCorners(24))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
    } else {
        this.isVisible = false
    }
}
