package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MyProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        // variable definition
        val btnMyProfileMain = findViewById<Button>(R.id.btnMyProfileMain)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // when btnMyProfileMain is pressed, jump to MyProfileActivity screen
        btnMyProfileMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // when btnLogout is pressed
        btnLogout.setOnClickListener {
            // logout the app
            FirebaseAuth.getInstance().signOut()
            // jump to LoginActivity screen
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}