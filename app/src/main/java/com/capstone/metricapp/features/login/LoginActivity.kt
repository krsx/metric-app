package com.capstone.metricapp.features.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.R
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityLoginBinding
import com.capstone.metricapp.features.home.HomeActivity
import com.capstone.metricapp.features.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvToRegister.setOnClickListener {
            val intentToRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentToRegister)
        }

        binding.edEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (!Patterns.EMAIL_ADDRESS.matcher(p0!!).matches()) {
                    binding.edEmail.error = getString(R.string.invalid_email)
                    binding.btnLogin.isEnabled = false
                } else {
                    binding.btnLogin.isEnabled = true
                }
            }
        })

        binding.edPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().length < 8) {
                    binding.edPass.error = getString(R.string.invalid_pass)
                    binding.btnLogin.isEnabled = false
                } else {
                    binding.btnLogin.isEnabled = true
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            if (isFormFilled()) {
                val intentToHome = Intent(this, HomeActivity::class.java)
                startActivity(intentToHome)
                finish()
            } else {
                showToast("Tolong isi semua data")
            }
        }
    }

    private fun isFormFilled(): Boolean {
        val editTextList = listOf(
            binding.edEmail,
            binding.edPass,
        )
        var isFilled = false

        for (editText in editTextList) {
            if (editText.text.toString().isNotEmpty()) {
                isFilled = true
            }
        }

        return isFilled
    }
}