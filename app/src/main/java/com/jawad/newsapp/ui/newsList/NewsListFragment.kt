package com.jawad.newsapp.ui.newsList

import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jawad.newsapp.R
import com.jawad.newsapp.di.ViewModelFactory
import com.jawad.newsapp.di.injectViewModel
import com.jawad.newsapp.ui.base.BaseFragment
import com.jawad.newsapp.ui.newsList.adapter.NewListAdapter
import com.jawad.newsapp.ui.newsList.adapter.VerticalItemDecoration
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import com.jawad.newsapp.data.remote.Result
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class NewsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ViewModelFactory

    private lateinit var newListViewModel: NewsListViewModel

    override val layoutId: Int
        get() = R.layout.fragment_news_list

    private var adapter: NewListAdapter? = null

    override fun initializeDagger() {
        newListViewModel = injectViewModel(viewModel)
    }

    override fun initializePresenter(view: View) {

        view.rv_newsList.setHasFixedSize(true)
        view.rv_newsList.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen._16sdp).toInt(), true)
        )
        if (adapter == null) {
            adapter = NewListAdapter()
            view.rv_newsList.adapter = adapter
            subscribeUi(view, adapter!!)
            newListViewModel.getNewsList()
        } else {
            view.rv_newsList.adapter = adapter
            view.progressBar.visibility = View.GONE
        }
    }

    private fun subscribeUi(view: View, adapter: NewListAdapter) {
        newListViewModel.mutableListLiveDataResult.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    view.progressBar.visibility = View.GONE
                    view.tv_header.visibility = View.VISIBLE
                    view.rv_newsList.visibility = View.VISIBLE
                    result.data?.let { adapter.submitList(it) }
                }
                Result.Status.LOADING -> {
                    view.progressBar.visibility = View.VISIBLE
                    view.rv_newsList.visibility = View.GONE
                }
                Result.Status.ERROR -> {
                    view.progressBar.visibility = View.GONE
                    Snackbar.make(view, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}
