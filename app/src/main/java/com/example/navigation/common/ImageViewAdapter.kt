package com.example.navigation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.bumptech.glide.Glide

/**
 * Created by Srikant Karnani on 30/7/19.
 */
class ImageViewAdapter {
    companion object {
        private const val BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w342"
        private const val BASE_ORIGINAL_IMAGE_URL: String = "https://image.tmdb.org/t/p/original"

        @JvmStatic
        @BindingAdapter("glideSrc")
        fun ImageView.setGlideSrc(url: String?) {
            val imageUrl: String = BASE_IMAGE_URL + url
            this.load(imageUrl)
//            Glide.with(context).load(imageUrl).into(this)
        }


        @JvmStatic
        @BindingAdapter("glideSrcOriginal")
        fun ImageView.setGlideSrcWithoutCache(url: String?) {
            val imageUrl: String = BASE_ORIGINAL_IMAGE_URL + url
            this.load(imageUrl)
//            Glide.with(context).asBitmap().load(imageUrl).into(this)
        }
    }
}