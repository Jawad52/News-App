package com.jawad.newsapp.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jawad.newsapp.ui.base.listeners.BaseView
import dagger.android.AndroidInjection

/**
 * The class BaseActivity
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {

    abstract val layoutId: Int

    protected abstract fun initializeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initializeViewModel()
    }

    /**
     * Fragmentation implementation can be used for navigation
     *
     * @param fragment
     * @param name
     */
    /*fun addFragment(fragment: Fragment, name: String) {
        if (supportFragmentManager.fragments.size == 0){
            val fm = supportFragmentManager.beginTransaction()
            fm.replace(R.id.nav_fragment, fragment, name)
            fm.addToBackStack(null)
            fm.commit()
        }
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}