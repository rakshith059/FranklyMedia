package com.example.franklymedia.viewmodels

data class LiveDataWrapper<T>(
    var data: T? = null,
    var error: Throwable? = null
)