package com.capstone.metricapp.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivitySplashBinding
import com.capstone.metricapp.features.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intentToLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentToLogin)
            intentToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
        }, DELAY.toLong())
    }

    companion object {
        const val DELAY = 3000
    }

}