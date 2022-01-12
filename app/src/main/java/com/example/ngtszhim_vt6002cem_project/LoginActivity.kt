package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // variable definition
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPwd = findViewById<EditText>(R.id.etLoginPwd)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnLoginSignUp = findViewById<Button>(R.id.btnLoginSignUp)

        // when btnLoginSignUp is pressed, jump to MyProfileActivity screen
        btnLoginSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // when the login button is pressed
        btnLogin.setOnClickListener {
            when {
                // when email field is empty
                TextUtils.isEmpty(
                    etLoginEmail.text.toString().trim { it <= ' ' }) -> {
                    // remind user to enter email
                    Toast.makeText(
                        this,
                        "Please enter your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // when password field is empty
                TextUtils.isEmpty(
                    etLoginPwd.text.toString().trim { it <= ' ' }) -> {
                    // remind user to enter password
                    Toast.makeText(
                        this,
                        "Please enter your password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    // put the text of the email field into val
                    val email: String = etLoginEmail.text.toString().trim { it <= ' ' }
                    // put the text of the password field into val
                    val pwd: String = etLoginPwd.text.toString().trim { it <= ' ' }

                    // login the app by email and password
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                // if the login is successfully,
                                // the system will remind user the login is successfully
                                Toast.makeText(
                                    this,
                                    "Login successful",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // put user id, email and password to intent,
                                // and jump to MainActivity screen
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                // if the login is not successfully,
                                // the system will remind user the login is not successfully
                                Toast.makeText(
                                    this,
                                    it.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }
}