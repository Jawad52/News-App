package com.jawad.newsapp.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * The class DateConverter
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 19 Mar 2020
 */
class DateConverter {
    companion object {
        /**
         * Convert date  format
         * @param date
         * @return Long data
         */
        @SuppressLint("SimpleDateFormat")
        fun getConvertedDate(time: String?): String? {
            val inputPattern = "yyyy-MM-dd'T'HH:mm:ss"
            val outputPattern = "dd MMMM"
            val inputFormat = SimpleDateFormat(inputPattern)
            val outputFormat = SimpleDateFormat(outputPattern)
            var date: Date? = null
            var str: String? = null
            try {
                date = inputFormat.parse(time)
                str = outputFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return str
        }

        @SuppressLint("SimpleDateFormat")
        fun parseTime(time: String?): String? {
            val inputPattern = "yyyy-MM-dd'T'HH:mm:ss"
            val outputPattern = "HH:mm"
            val inputFormat = SimpleDateFormat(inputPattern)
            val outputFormat = SimpleDateFormat(outputPattern)
            val date: Date?
            var str: String? = null
            try {
                date = inputFormat.parse(time)
                str = outputFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return str
        }
    }
}