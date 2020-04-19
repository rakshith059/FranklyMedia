package com.example.franklymedia.services

import android.content.Context
import com.example.franklymedia.MainApplication

object RandomDogService {
    var mRandomDogService: RandomDogService? = null
    var mRandomDogApiService: IRandomDogApiService? = null

    @Synchronized
    fun getInstance(targetContext: Context = MainApplication.mApplicationInstance?.applicationContext!!): RandomDogService {
        if (mRandomDogService == null) {
            mRandomDogService = RandomDogService

            mRandomDogApiService = RetrofitCacheApiClient(targetContext).mRetrofit.create(IRandomDogApiService::class.java)
        }
        return mRandomDogService as RandomDogService
    }

    fun getRandomDog() = mRandomDogApiService?.getRandomDogs()
}