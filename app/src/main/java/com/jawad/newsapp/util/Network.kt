package com.jawad.newsapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * The class Network
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

class Network {

    companion object Utils {
        private fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        fun isConnected(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected
        }
    }
}