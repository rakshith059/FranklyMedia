package com.example.franklymedia.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.franklymedia.utils.TABLE_RANDOM_DOGS

@Entity(tableName = TABLE_RANDOM_DOGS)
data class RandomDogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "message")
    var dogImage: String? = null
)