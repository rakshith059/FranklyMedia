package com.example.franklymedia.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.test.InstrumentationRegistry
import com.example.franklymedia.MainApplication
import com.example.franklymedia.models.RandomDogModel
import com.example.franklymedia.services.RandomDogService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RandomDogViewModel(application: Application = MainApplication.mApplicationInstance!!) : BaseViewModel<RandomDogModel>(application) {
    @SuppressLint("CheckResult")
    fun getRandomDog() {
        RandomDogService.getInstance()
            .getRandomDog()
            ?.subscribeOn(Schedulers.io())
            ?.retry(3)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ randomDogs ->
                notifyData(randomDogs)
            }, {
                notifyError(it)
            })
    }

    class Factory: ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RandomDogViewModel() as T
        }
    }
}