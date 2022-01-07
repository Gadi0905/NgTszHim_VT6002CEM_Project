package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // variable definition
        val btnLoginSignUp = findViewById<Button>(R.id.btnLoginSignUp)

        // when btnLoginSignUp is pressed, jump to MyProfileActivity screen
        btnLoginSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}