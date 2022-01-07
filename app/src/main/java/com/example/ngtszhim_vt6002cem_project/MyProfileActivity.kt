package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MyProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        // variable definition
        val btnMyProfileMain = findViewById<Button>(R.id.btnMyProfileMain)

        // when btnMyProfileMain is pressed, jump to MyProfileActivity screen
        btnMyProfileMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}