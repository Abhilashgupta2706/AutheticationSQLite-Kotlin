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
import com.practice.authenticationsqlite.db.UserDatabaseClass
import com.practice.authenticationsqlite.db.UserEntityClass
import kotlinx.coroutines.*

class Login : AppCompatActivity() {

    private lateinit var etLoginUsername: EditText
    private lateinit var etLoginPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var appDb: UserDatabaseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        appDb = UserDatabaseClass.getInstance(application)

        supportActionBar?.hide()
        val btnSignupRedirect = findViewById<TextView>(R.id.tvLoginNewUser)
        btnSignupRedirect.setOnClickListener {
            redirectToSignUp()
        }

        etLoginUsername = findViewById(R.id.etLoginUsername)
        etLoginPassword = findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun redirectToSignUp() {
        val intent = Intent(this, Signup::class.java)
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
    private fun loginUser() {
        val username = etLoginUsername.text.toString()
        val password = etLoginPassword.text.toString()

        // Validation Check
        if (username.isEmpty() or password.isEmpty()) {
            return toastMaker("Enter your credentials")
        }

        if (username.isNotEmpty() and password.isNotEmpty()) {

            var user: UserEntityClass? = appDb.daoClass().checkUsername(username)

            if (user == null) {
                return toastMaker("User not found")
            }

            if (password != user.password.toString()) {
                return toastMaker("Wrong Password")
            }

            Log.i("LOGIN - User Credentials", user.toString())

            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}