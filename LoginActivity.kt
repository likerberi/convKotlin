package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val PLButton = findViewById<Button>(R.id.PLButton)
        val CLButton = findViewById<Button>(R.id.CLButton)
        val loading = findViewById<ProgressBar>(R.id.loading)

        auth = FirebaseAuth.getInstance()
        // TODO Providers vs. Users.

        PLButton.setOnClickListener(this)
        CLButton.setOnClickListener (this)
    }

    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                }
            }

    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user
                }
            }
    }

    private fun validateForm() : Boolean {
        var valid = true

        val email = username.text.toString();
        if (TextUtils.isEmpty(email)) {
            username.error = "Required"
            valid = false
        } else {
            username.error = null
        }

        val pwd = password.text.toString()
        if (TextUtils.isEmpty(pwd)) {
            password.error = "Required"
            valid = false
        } else {
            password.error = null
        }

        return valid
    }

    override fun onClick(v: View) {
        val id = v.id
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when(id) {
            R.id.PLButton -> createAccount(username.text.toString(), password.text.toString())
            R.id.CLButton -> signIn(username.text.toString(), password.text.toString())
        }
    }
}