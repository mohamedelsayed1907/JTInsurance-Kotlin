package com.example.JTInsurance

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.JTInsurance.repository.InsuranceRepository
import com.example.JTInsurance.databinding.HomePolicyBinding
import com.example.JTInsurance.models.HomePolicy
import com.example.JTInsurance.models.HomeQuotes
import com.example.JTInsurance.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomePolicyActivity : AppCompatActivity() {

    private lateinit var binding: HomePolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getApi()
        mainPageButton()
    }

    private fun getApi() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.23:8080/")
            .build()

        val jsonPlaceHolderApi = retrofit.create(InsuranceRepository::class.java)
        val myCall: Call<List<HomePolicy>> = jsonPlaceHolderApi.getHomePolicies()

        myCall.enqueue(object: Callback<List<HomePolicy>> {
            override fun onResponse(call: Call<List<HomePolicy>>, response: Response<List<HomePolicy>>) {

                val HomePolicies: List<HomePolicy> = response.body()!!

                val startDate: TextView = findViewById(R.id.customerHStartDate)
                val endDate: TextView = findViewById(R.id.customerHEndDate)
                val premium: TextView = findViewById(R.id.customerPremium)
                val type: TextView = findViewById(R.id.policyType)
                val isActive: TextView = findViewById(R.id.isActive)


                for (policies in HomePolicies) {
                        startDate.text = policies.startDate.toString()
                        endDate.text = policies.endDate.toString()
                        premium.text = policies.premium.toString()
                        type.text = policies.type
                        isActive.text = policies.active.toString()

                        if (isActive.text == "true") {
                            isActive.text = "Yes"
                        } else {
                            isActive.text = "No"
                        }

                        if (type.text == "home") {
                            type.text = "Home"
                        }
                }
            }
            override fun onFailure(call: Call<List<HomePolicy>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    private fun mainPageButton() {
        var mainPageBtn: Button = findViewById(R.id.button)

        mainPageBtn.setOnClickListener{
            startActivity(Intent(this@HomePolicyActivity, MainActivity::class.java))
        }
    }

}