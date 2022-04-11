package com.example.JTInsurance

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ForgotPwdActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        forgotPassword()
    }

    private fun forgotPassword() {
        val forgotSubmitBtn: Button = findViewById(R.id.forgotSubmitBtn)

        forgotSubmitBtn.setOnClickListener {
            val pwdEmailText: TextInputEditText = findViewById(R.id.forgotPwdEmailText)
            val email: String = pwdEmailText.text.toString().trim { it <= ' '}
            if (email.isEmpty()) {
                Toast.makeText(this@ForgotPwdActivity, "Please enter email address", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@ForgotPwdActivity, "Reset email has been sent.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}