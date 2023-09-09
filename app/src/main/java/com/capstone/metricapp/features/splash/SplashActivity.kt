package com.capstone.metricapp.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivitySplashBinding
import com.capstone.metricapp.features.home.HomeActivity
import com.capstone.metricapp.features.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: ScanViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUserToken().observe(this) { token ->
            Handler(Looper.getMainLooper()).postDelayed({
                val intentFormSplash = if (token != "") {
                    Intent(this, HomeActivity::class.java)
                } else {
                    Intent(this, LoginActivity::class.java)
                }
                startActivity(intentFormSplash)
                intentFormSplash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                finish()
            }, DELAY.toLong())
        }
    }

    companion object {
        const val DELAY = 3000
    }

}