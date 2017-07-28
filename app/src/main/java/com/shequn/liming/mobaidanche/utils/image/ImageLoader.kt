package com.shequn.liming.mobaidanche.utils.image

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Liming on 2017/7/26.
 */
object ImageLoader {


    fun loadImage(context: Context, url: String?, imageview: ImageView) {
        if (TextUtils.isEmpty(url)) return
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageview)
    }


    fun loadCircleImage(context: Context, url: String?, imageview: ImageView) {
        if (TextUtils.isEmpty(url)) return
        Glide.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .transform(GlideCircleWhiteTransform(context))
                .into(imageview)

    }
}