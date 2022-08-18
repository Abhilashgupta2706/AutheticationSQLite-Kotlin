package com.practice.authenticationsqlite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.authenticationsqlite.db.UserDaoClass
import com.practice.authenticationsqlite.db.UserEntityClass
import kotlinx.coroutines.launch

class UsersViewModel(private val dao: UserDaoClass) : ViewModel() {

    private val users = dao.getAllUsers()

    fun addUser(userEntityClass: UserEntityClass) = viewModelScope.launch {
        dao.addUser(userEntityClass)
    }
}