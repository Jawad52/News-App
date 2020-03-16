package com.jawad.newsapp.ui.component.splash

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.jawad.newsapp.MainActivity
import com.jawad.newsapp.R
import com.jawad.newsapp.ui.base.BaseActivity
import com.jawad.newsapp.util.Constants
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun initializeViewModel() {
    }

    /**
     *  To show Splash UI in Full screen
     *  1) Disabled Title
     *  2) Window Flags is set to
     *      WindowManager.LayoutParams.FLAG_FULLSCREEN and
     *      WindowManager.LayoutParams.FLAG_FULLSCREEN
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        navigateToHomeScreen()
    }

    /**
     * Navigates to Home Screen after three sec's
     */
    private fun navigateToHomeScreen() {
        Handler().postDelayed({
            startActivity<MainActivity>()
            finish()
        }, Constants.SPLASH_DELAY.toLong())
    }
}
