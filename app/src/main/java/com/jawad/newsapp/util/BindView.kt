package com.jawad.newsapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jawad.newsapp.R

/**
 * The class BindView
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 14 Mar 2020
 */

/**
 * The image will be loaded into, cancels any existing loads into
 * the view, and frees any images have previously loaded into the view so they may be
 * reused.
 * @param view ImageView
 * @param imageUrl to display inamges from server
 */
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_launcher_background).error(
                    R.drawable.ic_launcher_background
                )
            )
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    } else Glide.with(view.context)
        .load(R.drawable.ic_launcher_background)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}