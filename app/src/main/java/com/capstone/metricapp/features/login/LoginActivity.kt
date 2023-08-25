package com.capstone.metricapp.features.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.R
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityLoginBinding
import com.capstone.metricapp.features.home.HomeActivity
import com.capstone.metricapp.features.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvToRegister.setOnClickListener {
            val intentToRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentToRegister)
        }

        if (binding.edEmail.text.isNullOrEmpty() || binding.edPass.text.isNullOrEmpty()) {
            binding.btnLogin.isEnabled = false
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
                } else binding.btnLogin.isEnabled = !binding.edPass.text.isNullOrEmpty()
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
                } else binding.btnLogin.isEnabled = !binding.edEmail.text.isNullOrEmpty()
            }
        })

        binding.btnLogin.setOnClickListener {
            if (!binding.edEmail.text.isNullOrEmpty() && !binding.edPass.text.isNullOrEmpty()) {
                val email = binding.edEmail.text.toString()
                val password = binding.edPass.text.toString()

                viewModel.loginUser(email, password).observe(this) { user ->
                    when (user) {
                        is Resource.Error -> {
                            showLoading(false)
                            buttonEnabled(true)
                            showLongToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                        }
                        is Resource.Loading -> {
                            showLoading(true)
                            buttonEnabled(false)
                        }
                        is Resource.Message -> {
                            Log.e("Login", user.message.toString())
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            buttonEnabled(true)
                            showLongToast("Selamat datang di aplikasi METRIC")

                            viewModel.saveUserToken("Bearer " + user.data?.token!!)
                            Log.e("DIVISI", user.data.division)
                            viewModel.saveUserDivision(user.data.division)
                            viewModel.saveUserEmail(user.data.email)

                            val intentToHome = Intent(this, HomeActivity::class.java)
                            startActivity(intentToHome)
                            finish()
                        }
                    }
                }
            } else {
                showToast("Tolong isi semua data")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun buttonEnabled(isEnabled: Boolean) {
        binding.btnLogin.isEnabled = isEnabled
    }
}