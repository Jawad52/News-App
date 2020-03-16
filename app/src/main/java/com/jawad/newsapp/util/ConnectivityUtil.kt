package com.jawad.newsapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * The class ConnectivityUtil
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 14 Mar 2020
 */
object ConnectivityUtil {
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}