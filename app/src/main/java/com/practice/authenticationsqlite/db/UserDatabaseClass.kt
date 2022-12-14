package com.practice.authenticationsqlite.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [UserEntityClass::class], version = 1, exportSchema = false)
abstract class UserDatabaseClass : RoomDatabase() {

    abstract fun daoClass(): UserDaoClass

    companion object {
        @Volatile
        // Initial value of instance is 0 i.e.., null
        private var INSTANCE: UserDatabaseClass? = null

        fun getInstance(context: Context): UserDatabaseClass {
            val tempInstance = INSTANCE

            // Returning same instance if database is already present
            if (tempInstance != null) {
                return tempInstance
            }

            // Else creating new database
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabaseClass::class.java,
                    "users_authentication_database"
                ).createFromAsset("database/database.db")
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }

        val PREPOPULATE_DATA = UserEntityClass(null, "admin", "admin")
    }

}