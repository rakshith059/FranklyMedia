package com.example.franklymedia.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.franklymedia.database.dao.RandomDogDao
import com.example.franklymedia.database.entity.RandomDogEntity
import com.example.franklymedia.utils.DATABASE_NAME

@Database(entities = [RandomDogEntity::class], version = 1)
abstract class RandomDogDatabase : RoomDatabase() {
    abstract fun getRandomDogDao(): RandomDogDao

    companion object {
        /**
         * `volatile`: that writes to this field are immediately made visible to other threads.
         */
        @Volatile
        private var ROOM_INSTANCE: RandomDogDatabase? = null

        fun getDatabase(mContext: Context): RandomDogDatabase? {
            if (null == ROOM_INSTANCE) {
                /**
                 * allowMainThreadQueries : Not recommended to use this but using for time being
                 */
                ROOM_INSTANCE = Room.databaseBuilder(mContext, RandomDogDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
            }
            return ROOM_INSTANCE
        }
    }
}
