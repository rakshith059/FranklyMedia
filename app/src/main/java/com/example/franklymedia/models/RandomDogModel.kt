package com.example.franklymedia.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RandomDogModel(
    @SerializedName("message")
    val randomDogImage: String? = null,
    @SerializedName("status")
    val randomDogStatus: String? = null
) : Parcelable