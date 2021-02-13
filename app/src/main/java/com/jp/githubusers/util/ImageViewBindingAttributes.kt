package com.jp.githubusers.util

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.jp.githubusers.GlideApp

object ImageViewBindingAttributes {

    @JvmStatic
    @SuppressLint("CheckResult")
    @BindingAdapter(value = ["image_src", "placeholder", "is_rounded"], requireAll = false)
    fun setImageUsingGlide(
        imageView: ImageView,
        url: String?,
        placeholder: Drawable?,
        isRounded: Boolean
    ) {
        if (url.isNullOrBlank()) return

        val request = GlideApp.with(imageView).load(url)
        placeholder?.let {
            request.placeholder(it)
            request.error(it)
        }

        if (isRounded) {
            request.apply(RequestOptions.circleCropTransform())
        }
        request.into(imageView)
    }
}
