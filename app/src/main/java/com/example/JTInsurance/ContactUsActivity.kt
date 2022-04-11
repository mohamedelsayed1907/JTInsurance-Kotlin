package com.example.JTInsurance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.JTInsurance.databinding.ContactUsBinding


class ContactUsActivity : AppCompatActivity() {

    private lateinit var binding: ContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phoneDialer()
    }

    private fun phoneDialer() {
        val phoneNumber: TextView = findViewById(R.id.phoneNumber)

        phoneNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:8778740323")
            startActivity(intent)
        }
    }
}