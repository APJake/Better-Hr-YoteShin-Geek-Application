package com.apjake.yoteshingeek.util

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.apjake.yoteshingeek.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation


fun ImageView.show(
    url: String,
    @DrawableRes error: Int = R.drawable.ic_launcher_background,
    @DrawableRes placeholder: Int = R.drawable.ic_launcher_foreground,
){
    Glide.with(this)
        .load(url)
        .error(error)
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.showBackground(
    url: String
){
    val multi = MultiTransformation<Bitmap>(
        BlurTransformation(5, 5),
        ColorFilterTransformation(0xbb000000.toInt())
    )
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.bitmapTransform(multi))
        .into(this)
}