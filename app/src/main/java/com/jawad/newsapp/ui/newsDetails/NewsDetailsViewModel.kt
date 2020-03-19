package com.jawad.newsapp.ui.newsDetails

import androidx.lifecycle.MutableLiveData
import com.jawad.newsapp.data.local.LocalRepository
import com.jawad.newsapp.data.local.model.NewsItem
import com.jawad.newsapp.di.CoroutineScropeIO
import com.jawad.newsapp.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The class NewsDetailsViewModel
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 18 Mar 2020
 */

class NewsDetailsViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    @CoroutineScropeIO private var coroutineScrope: CoroutineScope
) :
    BaseViewModel() {

    var newsId: Long? = null
    var newsModel: MutableLiveData<NewsItem> = MutableLiveData()

    fun getNewsById() {
        coroutineScrope.launch(Dispatchers.IO) {
            newsModel.postValue(localRepository.getNewsById(newsId))
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        coroutineScrope.cancel()
    }
}