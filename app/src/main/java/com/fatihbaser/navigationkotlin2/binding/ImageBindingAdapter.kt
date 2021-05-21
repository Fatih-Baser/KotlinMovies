package com.fatihbaser.navigationkotlin2.binding


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.fatihbaser.navigationkotlin2.util.Constant


@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(Constant.IMAGE_BASE_URL + Constant.IMAGE_W500 + url)
        .into(imageView)
}


