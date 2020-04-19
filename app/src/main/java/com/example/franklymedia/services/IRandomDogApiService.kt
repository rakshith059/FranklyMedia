package com.example.franklymedia.services

import com.example.franklymedia.models.RandomDogModel
import com.example.franklymedia.utils.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers

interface IRandomDogApiService {

    /**
     * Request type: GET
     * Request Header: "Content-Type: application/json; " + "charset=utf-8"
     *
     * URL: https://dog.ceo/api/breeds/image/random
     *
     * Response structure:
     *  {
            "message": "https://images.dog.ceo/breeds/****/****.jpg",
            "status": "success"
        }
     */
    @Headers(CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("breeds/image/random")
    fun getRandomDogs(): Flowable<RandomDogModel>
}