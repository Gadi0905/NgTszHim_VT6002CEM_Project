package com.example.ngtszhim_vt6002cem_project

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MyProfileActivity : AppCompatActivity() {
    private val cameraRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        // variable definition
        val imUserIcon = findViewById<ImageView>(R.id.imUserIcon)
        val tvUserId = findViewById<TextView>(R.id.tvUserId)
        val tvUserEmail = findViewById<TextView>(R.id.tvUserEmail)
        val btnCamera = findViewById<Button>(R.id.btnCamera)
        val btnGallery = findViewById<Button>(R.id.btnGallery)
        val btnMyProfileMain = findViewById<Button>(R.id.btnMyProfileMain)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        var (userId, email) = readUserData(tvUserId, tvUserEmail)

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

    private fun readUserData(tvUserId: TextView, tvUserEmail: TextView): Pair<String, String> {
        var userId = ""
        var email = ""
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        userId = document.data.getValue("user_id").toString()
                        email = document.data.getValue("email").toString()
                    }
                } else {
                }
                tvUserId.text = "User ID: $userId"
                tvUserEmail.text = "Email: $email"
            }
        return Pair(userId, email)
    }
}