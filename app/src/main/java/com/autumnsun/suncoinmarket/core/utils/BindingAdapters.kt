package com.autumnsun.suncoinmarket.core.utils

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
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
    imageUrl: String,
) {
    imageUrl.let {
        Glide.with(this.context)
            .load(it)
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