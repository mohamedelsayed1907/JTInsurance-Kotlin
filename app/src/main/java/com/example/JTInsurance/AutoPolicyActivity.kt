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
import com.example.JTInsurance.databinding.AutoPolicyBinding
import com.example.JTInsurance.repository.InsuranceRepository
import com.example.JTInsurance.databinding.HomePolicyBinding
import com.example.JTInsurance.models.HomePolicy
import com.example.JTInsurance.models.HomeQuotes
import com.example.JTInsurance.models.User
import com.example.JTInsurance.models.VehiclePolicy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AutoPolicyActivity : AppCompatActivity() {

    private lateinit var binding: AutoPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AutoPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getApi()
        mainPageButton()
        pleaseWaitToast()
    }

    private fun pleaseWaitToast() {
        Toast.makeText(applicationContext, "Displaying info... please wait...", Toast.LENGTH_LONG)
            .show()
    }

    private fun getApi() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.23:8080/")
            .build()

        val jsonPlaceHolderApi = retrofit.create(InsuranceRepository::class.java)
        val myCall: Call<List<VehiclePolicy>> = jsonPlaceHolderApi.getAutoPolicies()

        myCall.enqueue(object: Callback<List<VehiclePolicy>> {
            override fun onResponse(call: Call<List<VehiclePolicy>>, response: Response<List<VehiclePolicy>>) {

                val VehiclePolicies: List<VehiclePolicy> = response.body()!!

                val startDate: TextView = findViewById(R.id.customerAStartDate)
                val endDate: TextView = findViewById(R.id.customerAEndDate)
                val premium: TextView = findViewById(R.id.customerAPremium)
                val type: TextView = findViewById(R.id.policyAType)
                val isActive: TextView = findViewById(R.id.isActiveA)


                for (policies in VehiclePolicies) {
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

                    if (type.text == "vehicle") {
                        type.text = "Vehicle"
                    }
                }
            }
            override fun onFailure(call: Call<List<VehiclePolicy>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    private fun mainPageButton() {
        var mainPageBtn: Button = findViewById(R.id.button)

        mainPageBtn.setOnClickListener{
            startActivity(Intent(this@AutoPolicyActivity, MainActivity::class.java))
        }
    }

}