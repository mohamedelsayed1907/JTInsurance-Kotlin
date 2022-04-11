package com.example.JTInsurance

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.JTInsurance.api.Api
import com.example.JTInsurance.databinding.ContactUsBinding
import com.example.JTInsurance.databinding.CustomerInfoBinding
import com.example.JTInsurance.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomerInfoActivity : AppCompatActivity() {

    private lateinit var binding: CustomerInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getApi()
        mainPageButton()
    }

    private fun getApi() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.23:8080/")
            .build()

        val jsonPlaceHolderApi = retrofit.create(Api::class.java)
        val myCall: Call<List<User>> = jsonPlaceHolderApi.getUsers()

        myCall.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                val Users: List<User> = response.body()!!

                val firstName: TextView = findViewById(R.id.customerFirstName)
                val lastName: TextView = findViewById(R.id.customerLastName)
                val regEmail: TextView = findViewById(R.id.customerEmail)
                var phoneNumber: TextView = findViewById(R.id.customerPhoneNumber)
                var accidentRcrd: TextView = findViewById(R.id.accidentRecord)
                var streetAddress: TextView = findViewById(R.id.customerStreet)
                var city: TextView = findViewById(R.id.customerCity)
                var province: TextView = findViewById(R.id.customerProvince)
                var postalCode: TextView = findViewById(R.id.customerPostalCode)

                for (user in Users) {
                    if (user.id == LoginActivity.userId) {
                        firstName.text = user.firstName
                        lastName.text = user.lastName
                        regEmail.text = user.email
                        phoneNumber.text = user.phone
                        accidentRcrd.text = user.accidentAmt.toString()
                        streetAddress.text = user.address
                        city.text = user.city
                        province.text = user.province
                        postalCode.text = user.postalcode
                    }
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    private fun mainPageButton() {
        var mainPageBtn: Button = findViewById(R.id.button)

        mainPageBtn.setOnClickListener{
            startActivity(Intent(this@CustomerInfoActivity, MainActivity::class.java))
        }
    }

}