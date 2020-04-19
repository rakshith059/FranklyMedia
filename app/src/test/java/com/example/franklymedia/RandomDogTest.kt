package com.example.franklymedia

import androidx.test.InstrumentationRegistry
import com.example.franklymedia.services.RandomDogService
import org.junit.After
import org.junit.Before
import org.junit.Test

class RandomDogTest {
    var mRandomDogsService: RandomDogService? = null
    @Before
    fun init() {
    }

    @Test
    fun getRandomDogs() {
        mRandomDogsService
            ?.getRandomDog()
            ?.subscribe({
                println("$it")
            }, {
                println("$it")
            })
    }

    @After
    fun unInit() {
        mRandomDogsService = null
    }
}