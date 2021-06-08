package com.example.fastre.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.fastre.R
import com.example.fastre.ui.authentication.AuthenticationActivity
import com.example.fastre.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        private lateinit var auth : FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        loginAnything()

    }

    private fun loginAnything() {
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                startActivity(intent)
                finish()}, 3000)

        }
        else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@SplashScreenActivity, AuthenticationActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }

    }

}