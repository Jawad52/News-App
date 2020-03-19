package com.jawad.newsapp.ui.newsDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jawad.newsapp.R
import com.jawad.newsapp.di.ViewModelFactory
import com.jawad.newsapp.di.injectViewModel
import com.jawad.newsapp.ui.base.BaseFragment
import com.jawad.newsapp.util.DateConverter
import com.jawad.newsapp.util.bindImageFromUrl
import kotlinx.android.synthetic.main.fragment_news_details.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class NewsDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var newsDetailsViewModel: NewsDetailsViewModel

    private val args: NewsDetailsFragmentArgs by navArgs()

    override val layoutId: Int
        get() = R.layout.fragment_news_details

    override fun initializeDagger() {
        newsDetailsViewModel = injectViewModel(viewModelFactory)
    }

    override fun initializePresenter(view: View) {
        view.iv_close.setOnClickListener {
            activity!!.onBackPressed()
        }

        newsDetailsViewModel.newsId = args.newsId
        subscribeUi(view)
        newsDetailsViewModel.getNewsById()
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeUi(view: View) {
        newsDetailsViewModel.newsModel.observe(this, Observer {
            view.tv_des_title.text = it.title
            view.tv_des_article.text = "Article \n${it.byline}"
            var date = it.updatedDate
            date = date.subSequence(0, date.lastIndexOf("-")).toString()
            val dateText = DateConverter.parseDate(date)
            val time = DateConverter.parseTime(date)
            val textDate = "$dateText    $time"

            val spanDate = SpannableString(textDate)
            spanDate.setSpan(
                AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen._10ssp)), 0,
                textDate.length,
                SPAN_INCLUSIVE_INCLUSIVE
            )
            spanDate.setSpan(
                ForegroundColorSpan(resources.getColor(R.color.greyChateau, null)),
                0,
                textDate.length,
                0
            )

            val descriptionText = it.abstractText
            val spanDesc = SpannableString(descriptionText)
            spanDesc.setSpan(
                AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen._14ssp)), 0,
                descriptionText.length,
                SPAN_INCLUSIVE_INCLUSIVE
            )
            spanDesc.setSpan(
                ForegroundColorSpan(resources.getColor(R.color.white, null)),
                0,
                descriptionText.length,
                0
            )
            val finalText = TextUtils.concat(
                spanDate,
                System.lineSeparator() +
                        System.lineSeparator(), spanDesc
            )
            view.tv_description.text = finalText

            view.ib_share.setOnClickListener { _ ->
                shareInformation("*${it.title}*", it.abstractText, it.url)
            }
            if (!it.multimedia.isNullOrEmpty()) {
                for (newItem in it.multimedia) {
                    if (newItem.format == "superJumbo") {
                        bindImageFromUrl(view.iv_photo, newItem.url)
                        if (newItem.copyright.isNotEmpty()) {
                            view.tv_copyright.text = "Copyright © ${newItem.copyright}"
                        } else {
                            view.tv_copyright.visibility = View.GONE
                        }
                        break
                    }
                }
            }
        })
    }

    /**
     * To share content across multiple channels, including email,
     * text messaging, social networking and more
     *
     * @param title
     * @param subject, content to share the data
     * @param url, URL of the content for more information
     */
    private fun shareInformation(title: String, subject: String, url: String) {
        val shareText =
            title +
                    System.lineSeparator() +
                    System.lineSeparator() +
                    subject +
                    System.lineSeparator() +
                    System.lineSeparator() +
                    url
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, title)
        intent.putExtra(Intent.EXTRA_TEXT, shareText)
        startActivity(Intent.createChooser(intent, "Share via"))
    }
}
