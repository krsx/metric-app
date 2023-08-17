package com.capstone.metricapp.features.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import com.capstone.metricapp.R
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.utils.constans.divisionsItems
import com.capstone.metricapp.core.utils.hideKeyboard
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityRegisterBinding
import com.capstone.metricapp.features.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPickDivision()

        binding.tvToLogin.setOnClickListener {
            val intentToLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentToLogin)
        }

        binding.edEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (!Patterns.EMAIL_ADDRESS.matcher(p0!!).matches()) {
                    binding.edEmail.error = getString(R.string.invalid_email)
                    binding.btnRegister.isEnabled = false
                } else {
                    binding.btnRegister.isEnabled = true
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
                    binding.btnRegister.isEnabled = false
                } else {
                    binding.btnRegister.isEnabled = true
                }
            }
        })

        binding.edPassConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().length < 8) {
                    binding.edPassConfirm.error = getString(R.string.invalid_pass)
                    binding.btnRegister.isEnabled = false
                } else {
                    binding.btnRegister.isEnabled = true
                }
            }
        })

        binding.edPassConfirm.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_NEXT || keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard()
                return@setOnEditorActionListener true
            }
            false
        }

        binding.btnRegister.setOnClickListener {
            if (binding.edPass.text != binding.edPassConfirm) {
                showToast("Pastikan password anda masukkan adalah sama")
            } else if (isFormFilled()) {
                val email = binding.edEmail.text.toString()
                val password = binding.edPass.text.toString()
                val division = binding.dropdownMenu.text.toString()

                viewModel.registerUser(email, password, division).observe(this) { user ->
                    when (user) {
                        is Resource.Error -> {
                            buttonEnabled(true)
                            showLoading(false)
                            showToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                        }
                        is Resource.Loading -> {
                            buttonEnabled(false)
                            showLoading(true)
                        }
                        is Resource.Message -> {
                            Log.e("Register", user.message.toString())
                        }
                        is Resource.Success -> {
                            buttonEnabled(true)
                            showLoading(false)
                            showLongToast("Akun telah berhasil didaftarkan, silahkan login untuk dapat menggunakan aplikasi")

                            val intentToLogin = Intent(this, LoginActivity::class.java)
                            startActivity(intentToLogin)
                            finish()
                        }
                    }
                }
            } else {
                showToast("Pastikan semua data telah terisi!")
            }
        }
    }

    private fun setupPickDivision() {
        val adapter = ArrayAdapter(this, R.layout.item_list_dropdown, divisionsItems)
        binding.dropdownMenu.setAdapter(adapter)
        binding.dropdownMenu.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.edDivision.isHintEnabled = p0.isNullOrEmpty()
            }

        })
    }

    private fun isFormFilled(): Boolean {
        val editTextList = listOf(
            binding.edEmail,
            binding.edPass,
            binding.edPassConfirm,
        )
        var isFilled = false

        for (editText in editTextList) {
            if (editText.text.toString().isNotEmpty() and binding.edDivision.isNotEmpty()) {
                isFilled = true
            }
        }

        return isFilled
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun buttonEnabled(isEnabled: Boolean) {
        binding.btnRegister.isEnabled = isEnabled
    }
}