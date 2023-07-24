package com.capstone.metricapp.features.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import com.capstone.metricapp.R
import com.capstone.metricapp.core.utils.divisionsItems
import com.capstone.metricapp.core.utils.hideKeyboard
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityRegisterBinding
import com.capstone.metricapp.features.home.HomeActivity
import com.capstone.metricapp.features.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

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
            if (isFormFilled()) {
                val intentToLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentToLogin)
                finish()
            } else if (!isFormFilled())
                showToast("Pastikan semua data telah terisi")
            else if (binding.edPass.text != binding.edPassConfirm) {
                showToast("Pastikan password anda masukkan adalah sama")
            } else {
                showToast("Pastikan semua data telah terisi")
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
}