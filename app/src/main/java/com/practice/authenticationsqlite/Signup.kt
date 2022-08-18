package com.practice.authenticationsqlite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.practice.authenticationsqlite.db.UserDatabaseClass
import com.practice.authenticationsqlite.db.UserEntityClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Signup : AppCompatActivity() {

    private lateinit var etSignupUsername: EditText
    private lateinit var etSignupPassword: EditText
    private lateinit var etSignupConfirmPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var appDb: UserDatabaseClass

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        appDb = UserDatabaseClass.getInstance(application)

        supportActionBar?.hide()

        val btnLoginRedirect = findViewById<TextView>(R.id.tvSignupAlreadyUser)
        btnLoginRedirect.setOnClickListener {
            redirectToLogin()
        }

        etSignupUsername = findViewById(R.id.etSignupUsername)
        etSignupPassword = findViewById(R.id.etSignupPassword)
        etSignupConfirmPassword = findViewById(R.id.etSignupConfirmPassword)
        btnSignup = findViewById(R.id.btnSingup)

        val dao = UserDatabaseClass.getInstance(application).daoClass()
        val factory = UserViewModelFactory(dao)
        usersViewModel = ViewModelProvider(this, factory)[UsersViewModel::class.java]


        btnSignup.setOnClickListener {
            signup()
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun toastMaker(message: String) {
        return Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    @SuppressLint("LongLogTag")
    private fun signup() {
        val username = etSignupUsername.text.toString()
        val password = etSignupPassword.text.toString()
        val confirmPassword = etSignupConfirmPassword.text.toString()

        var userExist: UserEntityClass? = appDb.daoClass().checkUsername(username)
        var userExistLength: Int? = userExist.toString().length

        Log.i("SIGNING - UserCheck", userExist.toString())
        Log.i("SIGNING - UserCheck", userExistLength.toString())

        if (userExist != null) {
            return toastMaker("Username already taken")
        }

        // Validation Check
        if (username.isEmpty() or password.isEmpty()) {
            return toastMaker("Make sure all details are filled")
        }

        val passLength = 5
        if (password.length < passLength) {
            val message = "Password must be Greater then $passLength characters"
            return toastMaker(message)
        }

        if (confirmPassword != password) {
            return toastMaker("Password doesn't Match")
        }

        val user = UserEntityClass(null, username, password)
        Log.i("SIGNING - Inserting User", user.toString())
        usersViewModel.addUser(user)
        redirectToLogin()
    }

}