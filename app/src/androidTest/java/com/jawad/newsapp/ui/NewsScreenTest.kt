package com.jawad.newsapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.jawad.newsapp.MainActivity
import com.jawad.newsapp.R
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The class NewsScreenTest
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 21 Mar 2020
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsScreenTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    private var mIdlingResource: IdlingResource? = null

    @Before
    fun setUp() {
        mIdlingResource = mActivityTestRule.activity.countingIdlingResource
        IdlingRegistry.getInstance().register(mIdlingResource)
    }

    @Test
    fun initialTest(){
        Espresso.onView(ViewMatchers.withId(R.id.tv_header)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(
            ViewAssertions.matches(
                Matchers.not(
                    ViewMatchers.isDisplayed()
                )
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.rv_newsList)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @Test
    fun displayNewsData() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_newsList)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(
            ViewAssertions.matches(
                Matchers.not(
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }

    @Test
    fun testScroll(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_newsList)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        for (i in 0 until mActivityTestRule.activity.rv_newsList.adapter!!.itemCount - 1)
            Espresso.onView(ViewMatchers.withId(R.id.rv_newsList))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        i,
                        ViewActions.scrollTo()
                    )
                )
    }

    @Test
    fun testNewsDetailPage(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_newsList)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.rv_newsList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.iv_photo)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.tv_des_title)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.tv_description)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.rv_newsList)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}