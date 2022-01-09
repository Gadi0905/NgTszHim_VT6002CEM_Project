package com.example.ngtszhim_vt6002cem_project

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Manifest

class MyProfileActivity : AppCompatActivity() {

    companion object {
        private const val cameraPermissionCode = 1
        private const val cameraRequestCode = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        // variable definition
        val tvUserId = findViewById<TextView>(R.id.tvUserId)
        val tvUserEmail = findViewById<TextView>(R.id.tvUserEmail)
        val btnCamera = findViewById<Button>(R.id.btnCamera)
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

        btnCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, cameraRequestCode)
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.CAMERA),
                    cameraPermissionCode
                )
            }
        }
    }

    // get the permissions of the camera
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, cameraRequestCode)
            } else {
                Toast.makeText(
                    this,
                    "Please allow the permission for camera in the settings",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // variable definition
        val ivUserIcon = findViewById<ImageView>(R.id.ivUserIcon)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == cameraRequestCode) {
                val thumbNail: Bitmap = data!!.extras!!.get("data") as Bitmap
                ivUserIcon.setImageBitmap(thumbNail)
            }
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
                }
                tvUserId.text = "User ID: $userId"
                tvUserEmail.text = "Email: $email"
            }
        return Pair(userId, email)
    }
}