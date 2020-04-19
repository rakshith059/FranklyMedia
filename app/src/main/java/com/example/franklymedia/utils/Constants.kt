package com.example.franklymedia.utils

import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import java.lang.Exception
import java.util.*
import kotlin.math.roundToInt

//https://dog.ceo/api/breeds/image/random
const val BASE_URL = "https://dog.ceo/api/"
const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " + "charset=utf-8"

/**
 * constants for room DB
 */
const val DATABASE_NAME = "RandomDogs.db"
const val TABLE_RANDOM_DOGS = "TABLE_RANDOM_DOGS"

/**
 * constant for width percentage
 */
const val WIDTH_PERCENTAGE_75: Double = 0.75

const val SPLASH_SCREEN_DELAY_MILLI_SEC: Long = 1000

fun isNetworkAvailable(mContext: Context): Boolean {
    return try {
        val connectivityManager: ConnectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.isConnected
    } catch (e: Exception) {
        false
    }
}

/**
 * Extension for printing the debug logs
 */
fun logdExt(message: String) {
    val stackTraceElement = Thread.currentThread().stackTrace
    val tag = String.format(Locale.getDefault(), "${stackTraceElement[3].fileName} ${stackTraceElement[3].methodName} == ")
    Log.d(tag, message)
}

/**
 * To calculate %percent of screen width
 */
fun calculateScreenWidth(mContext: Context, widthPercentage: Double): Int {
    val screenWidth = getScreenWidth(mContext)
    return (screenWidth * widthPercentage).roundToInt()
}

/**
 * function for getting screen width
 */
fun getScreenWidth(context: Context?): Int {
    val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}