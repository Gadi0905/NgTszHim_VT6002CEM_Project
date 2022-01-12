package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // variable definition
        val btnMainMyProfile = findViewById<Button>(R.id.btnMainMyProfile)
        val btnMap = findViewById<Button>(R.id.btnMap)

        // when btnMainMyProfile is pressed, jump to MyProfileActivity screen
        btnMainMyProfile.setOnClickListener {
            startActivity(Intent(this, MyProfileActivity::class.java))
        }

        // when btnMap is pressed, jump to MapsActivity screen
        btnMap.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }
}