package com.practice.authenticationsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var pressedTime: Long = 0

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

    override fun onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finishAffinity()
            exitProcess(0)
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()
    }
}