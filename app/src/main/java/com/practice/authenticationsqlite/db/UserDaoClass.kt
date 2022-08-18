package com.practice.authenticationsqlite.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDaoClass {

    @Insert
    suspend fun addUser(user: UserEntityClass)

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): LiveData<List<UserEntityClass>>

//    @Query("SELECT EXISTS(SELECT * FROM users_table where username=:username AND user_password=:password)")
//    fun loginUser(username: String, password: String)
}
