package com.jawad.newsapp.ui.newsList.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jawad.newsapp.data.local.model.NewsItem
import com.jawad.newsapp.ui.newsList.NewsListFragmentDirections
import com.jawad.newsapp.util.DateConverter
import com.jawad.newsapp.util.bindImageFromUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

/**
 * The class NewItemViewHolder
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 18 Mar 2020
 */

class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    /**
     * Called when onBindViewHolder is triggered in RecyclerView adapter
     * The new bind will be used to set the values to display items
     * @param newsEntity contains the values to set in UI
     */
    fun bind(newsItem: NewsItem) {
        tv_title.text = newsItem.title ?: ""
        tv_caption.text = newsItem.byline ?: ""
        var date = newsItem.updatedDate
        date = date.subSequence(0, date.lastIndexOf("-")).toString()
        tv_time.text = DateConverter.getConvertedDate(date)

        if (!newsItem.multimedia.isNullOrEmpty()) {
            for (newItem in newsItem.multimedia) {
                if (newItem.format == "thumbLarge") {
                    bindImageFromUrl(iv_news_item_image, newItem.url)
                    break
                }
            }
        }

        cl_news_item.setOnClickListener {
            val action =
                NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(newsItem.id)
            it.findNavController().navigate(action)
        }
    }
}