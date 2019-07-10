package com.example.searchcase.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.searchcase.R

object ViewDataBinding {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: ImageView, imageUrl: String?) {
        val imageWidth =
            (DeviceUtils.getDeviceWidth(imageView.context)) - imageView.context.resources.getDimension(
                R.dimen.margin_16dp
            )
        val requestOption =
            RequestOptions.overrideOf(imageWidth.toInt(), (imageWidth * 0.70).toInt())

        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(requestOption)
            .into(imageView)
    }

}
