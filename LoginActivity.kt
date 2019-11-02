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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.kakao.auth.*
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_login.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.kakao.usermgmt.LoginButton
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLogin.mOAuthLoginHandler
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val TAG = LoginActivity::class.java!!.simpleName
        // from getSimpleName()
        private val OAUTH_CLIENT_ID = "BLOCK!!"
        private val OAUTH_CLIENT_SECRET = "BLOCK!!"
        private val OAUTH_CLIENT_NAME = "BLOCK!!"
    }

    private lateinit var kakaoCallback: SessionCallback
    private lateinit var auth: FirebaseAuth

    private lateinit var mAuthLoginModule: OAuthLogin
    private lateinit var mOAuthLoginButton: OAuthLoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // for NAver
        mAuthLoginModule = OAuthLogin.getInstance()
        mAuthLoginModule.init(
            this, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME
        )


        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val PLButton = findViewById<Button>(R.id.PLButton)
        val CLButton = findViewById<Button>(R.id.CLButton)
        //val kakaoLoginButton = findViewById<Button>(R.id.kakaoLoginButton)
        val loading = findViewById<ProgressBar>(R.id.loading)

        auth = FirebaseAuth.getInstance()
        kakaoCallback = SessionCallback()
        Session.getCurrentSession().addCallback(kakaoCallback) //
        Session.getCurrentSession().checkAndImplicitOpen()

        // TODO Providers vs. Users.
        // PLButton.isEnabled() to click or enable.

        PLButton.isEnabled = true
        CLButton.isEnabled = true

        PLButton.setOnClickListener(this)
        CLButton.setOnClickListener(this)
        kakaoLoginButton.setOnClickListener(this)
        buttonOAuthLoginImg.setOnClickListener(this)
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

    fun kakaoLogin() {
        Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, this)
    }

    override fun onClick(v: View) {
        val id = v.id

        if (!validateForm()) {
            return
        }

        when(id) {
            R.id.PLButton -> createAccount(username.text.toString(), password.text.toString())
            R.id.CLButton -> signIn(username.text.toString(), password.text.toString())
            R.id.kakaoLoginButton -> kakaoLogin()
            R.id.buttonOAuthLoginImg -> mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        }
    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            Log.d(TAG, "SessionStatusCallback.onSessionOpenFailed exception:$exception")
        }

        override fun onSessionOpened() {
            kakaoRequestMe()
        }
    }

    private fun kakaoRequestMe() {
        UserManagement.getInstance().me(object: MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                val kakaoIntent = Intent(applicationContext, ProviderActivity::class.java)
                startActivity(kakaoIntent)

            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.d(TAG, "SessionStatusCallback.onSessionOpenFailed exception:$errorResult")
            }
        })
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
        mAuthLoginModule.logout(this);
    }
}