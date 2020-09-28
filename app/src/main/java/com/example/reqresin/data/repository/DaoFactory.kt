package com.example.reqresin.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reqresin.data.repository.daos.UserDao
import com.example.reqresin.data.repository.record.UserRecord

@Database(
    entities = [
        UserRecord::class
    ], version = 1,
    exportSchema = false
)
abstract class DaoFactory : RoomDatabase()  {

    companion object {

        private const val NAME = "storage1.db"

        fun create(context: Context) = Room
            .databaseBuilder(context, DaoFactory::class.java, NAME)
            .build()
    }

    abstract fun userDao(): UserDao
}