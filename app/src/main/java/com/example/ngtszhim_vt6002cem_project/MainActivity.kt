package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

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
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(this, MapsActivity::class.java))
            } else {
                Toast.makeText(
                    this,
                    "Please allow the permission for location in the settings",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}