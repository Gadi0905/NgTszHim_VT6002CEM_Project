package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // variable definition
        val etSignUpEmail = findViewById<EditText>(R.id.etSignUpEmail)
        val etSignUpPwd = findViewById<EditText>(R.id.etSignUpPwd)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val btnSignUpLogin = findViewById<Button>(R.id.btnSignUpLogin)

        // when btnSignUpLogin is pressed, jump to LoginActivity screen
        btnSignUpLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // when btnSignUp is pressed
        btnSignUp.setOnClickListener {
            when {
                // when email field is empty
                TextUtils.isEmpty(
                    etSignUpEmail.text.toString().trim { it <= ' ' }) -> {
                    // remind user to enter email
                    Toast.makeText(
                        this,
                        "Please enter your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // when password field is empty
                TextUtils.isEmpty(
                    etSignUpPwd.text.toString().trim { it <= ' ' }) -> {
                    // remind user to enter password
                    Toast.makeText(
                        this,
                        "Please enter your password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    // put the text of the email field into val
                    val email: String = etSignUpEmail.text.toString().trim { it <= ' ' }
                    // put the text of the password field into val
                    val pwd: String = etSignUpPwd.text.toString().trim { it <= ' ' }

                    // create account by email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                // if the account is successfully created
                                val firebaseUser: FirebaseUser = it.result!!.user!!

                                // remind user the account is successfully created
                                Toast.makeText(
                                    this,
                                    "Account successfully created",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // call the saveUserData function
                                // and save the user data at the same time
                                saveUserData(firebaseUser.uid, email)

                                // put user id and email to intent,
                                // and jump to MainActivity screen
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                // if the account is not successfully created,
                                // the system will remind user the account is not successfully created
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
    // save user data function
    private fun saveUserData(uid: String, email: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()

        // put the uid into firebase user's user_id field
        user["user_id"] = uid
        // put the email into firebase user's email field
        user["email"] = email

        // collect to firebase's users table and add user
        db.collection("users")
            .add(user)
    }
}