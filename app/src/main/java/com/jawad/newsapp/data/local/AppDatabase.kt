package com.jawad.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jawad.newsapp.data.local.dao.NewsDao
import com.jawad.newsapp.data.local.model.NewsEntity
import com.jawad.newsapp.data.remote.dto.Multimedia

/**
 * The class AppDatabase
 *
 * @author  Jawad Usman
 * @web www.jawadusman.com
 * @version 1.0
 * @since 15 Mar 2020
 */

@Database(
    entities = [NewsEntity::class, Multimedia::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }
            }
            return INSTANCE!!
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "news-db")
                .build()
        }
    }
}