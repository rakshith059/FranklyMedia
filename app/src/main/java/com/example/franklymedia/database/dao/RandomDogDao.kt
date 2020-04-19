package com.example.franklymedia.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.franklymedia.database.entity.RandomDogEntity

@Dao
interface RandomDogDao {
    /**
     * inserting randomDogEntity to the table
     * OnConflictStrategy.REPLACE : will always insert a row even if there is a conflict and old will be replaced by new
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRandomDog(randomDog: RandomDogEntity)

    /**
     * getting 20 data from TABLE_RANDOM_DOGS table in descending order
     */
    @Query("SELECT * FROM TABLE_RANDOM_DOGS limit 20")
    fun getAllRandomDogs(): List<RandomDogEntity>

    /**
     * deleting the last recently used item from DB
     */
    @Query("DELETE FROM TABLE_RANDOM_DOGS where id= (SELECT MIN(id) FROM TABLE_RANDOM_DOGS)")
    fun deleteLRURandomDog()

    /**
     * delete the items inside TABLE_RANDOM_DOGS
     */
    @Query("DELETE FROM TABLE_RANDOM_DOGS")
    fun deleteAllRandomDogs()
}