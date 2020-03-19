package com.jawad.newsapp.ui.newsList

import androidx.lifecycle.MutableLiveData
import com.jawad.newsapp.data.DataRepository
import com.jawad.newsapp.data.local.model.NewsItem
import com.jawad.newsapp.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import com.jawad.newsapp.data.remote.Result
import com.jawad.newsapp.di.CoroutineScropeIO
import javax.inject.Inject

/**
 * The class NewsListViewModel
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 18 Mar 2020
 */

/**
 * The ViewModel for [NewsListViewModel].
 */
class NewsListViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    @CoroutineScropeIO private val coroutineScope: CoroutineScope
) : BaseViewModel() {

    var mutableListLiveDataResult: MutableLiveData<Result<List<NewsItem>>> = MutableLiveData()

    /**
     * Get News list from data repository
     */
    fun getNewsList() {
        dataRepository.observeNews(coroutineScope) {
            mutableListLiveDataResult.postValue(it)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}