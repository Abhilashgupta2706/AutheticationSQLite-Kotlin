package com.practice.authenticationsqlite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.authenticationsqlite.db.UserDaoClass
import java.lang.IllegalArgumentException

class UserViewModelFactory(
    private val dao: UserDaoClass
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}