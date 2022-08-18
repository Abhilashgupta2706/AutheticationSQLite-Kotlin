package com.practice.authenticationsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fabGetAllUsers: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabGetAllUsers = findViewById(R.id.fabGetAllUsers)
        fabGetAllUsers.setOnClickListener {
            val intent = Intent(this, AllUsers::class.java)
            startActivity(intent)
        }
    }
}