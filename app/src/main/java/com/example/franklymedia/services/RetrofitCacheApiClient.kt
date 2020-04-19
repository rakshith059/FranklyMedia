package com.example.franklymedia.services

import android.content.Context
import com.example.franklymedia.utils.BASE_URL
import com.example.franklymedia.utils.isNetworkAvailable
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitCacheApiClient(val mContext: Context) {
    private val myCache = Cache(mContext.cacheDir, cacheSize)

    /**
     * interceptor and builder is used to print the log
     */
    private var httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor(httpLoggingInterceptor) // adding log interceptor
        .addInterceptor { chain ->
            // Get the request from the chain.
            var request = chain.request()
            request = if (isNetworkAvailable(mContext))
            /**
             * If there is internet, get the cache that was stored 5 sec ago. If cache is older than 5 sec then discard it and show error from API
             */
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
            /**
             * if there is no internet, get the cache that wad stored 5 days ago, if it is older discard the cache.
             * "only-if-cached" : not retrive new data, fetch the data from cache only
             */
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=${TimeUnit.DAYS.toMillis(5)}").build()

            chain.proceed(request)
        } // adding CACHE interceptor
        .connectTimeout(30, TimeUnit.SECONDS) // giving 30 sec as connection timeout
        .build()

    var mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // for Gson
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // for reactivex
        .client(okHttpClient)
        .build()

    companion object {
        private const val cacheSize = (5 * 1024 * 1024).toLong()
    }
}