package com.practice.authenticationsqlite.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDaoClass {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserEntityClass)

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): LiveData<List<UserEntityClass>>

    @Query("SELECT * FROM users_table WHERE username LIKE:username LIMIT 1")
    fun checkUsername(username: String): UserEntityClass

    @Query("SELECT * FROM users_table WHERE username LIKE:username AND user_password LIKE:password LIMIT 1")
    fun loginUser(username: String, password: String): UserEntityClass
}
