package com.example.franklymedia.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.franklymedia.utils.logdExt

abstract class BaseViewModel<T>(application: Application) : ViewModel() {
    private var liveData: MutableLiveData<LiveDataWrapper<T>> = MutableLiveData()

    fun getLiveDataObservable() = liveData

    protected fun notifyData(data: T) {
        logdExt("Data Notified :$data")
        liveData.value = LiveDataWrapper(data = data)
    }

    protected fun notifyError(error: Throwable) {
        logdExt("Error :$error")
        liveData.value = LiveDataWrapper(error = error)
    }
}