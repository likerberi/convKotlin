package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var kakaoCallback: ISessionCallback
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val PLButton = findViewById<Button>(R.id.PLButton)
        val CLButton = findViewById<Button>(R.id.CLButton)
        val loading = findViewById<ProgressBar>(R.id.loading)

        auth = FirebaseAuth.getInstance()
        kakaoCallback = SessionCallback()
        Session.getCurrentSession().addCallback(kakaoCallback)
        Session.getCurrentSession().checkAndImplicitOpen()

        // TODO Providers vs. Users.
        // PLButton.isEnabled() to click or enable.

        PLButton.isEnabled = true
        CLButton.isEnabled = true

        PLButton.setOnClickListener(this)
        CLButton.setOnClickListener (this)
    }

    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth = FirebaseAuth.getInstance()
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser?.email
                    Toast.makeText(
                        applicationContext,
                        user,
                        Toast.LENGTH_LONG
                    ).show()
                    val nextIntent = Intent(this, ProviderActivity::class.java)
                    startActivity(nextIntent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        applicationContext,
                        "create_error",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser?.email
                    // displayName is nulll...........
                    Toast.makeText(
                        applicationContext,
                        user,
                        Toast.LENGTH_LONG
                    ).show()
                    val nextIntent = Intent(this, ProviderActivity::class.java)
                    startActivity(nextIntent)
                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(
                        applicationContext,
                        "signin_error",
                        Toast.LENGTH_LONG
                    ).show()
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

        if (!validateForm()) {
            return
        }

        when(id) {
            R.id.PLButton -> createAccount(username.text.toString(), password.text.toString())
            R.id.CLButton -> signIn(username.text.toString(), password.text.toString())
        }
    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSessionOpened() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            //
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ( Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(kakaoCallback)
    }
}