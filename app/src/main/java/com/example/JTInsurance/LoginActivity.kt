package com.example.JTInsurance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.JTInsurance.databinding.LoginMainBinding
import com.example.JTInsurance.models.User
import com.example.JTInsurance.repository.InsuranceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class LoginActivity : AppCompatActivity(), CoroutineScope {


    private lateinit var viewModel: ViewModel
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: LoginMainBinding

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        animateLogo()
        login()
        forgotPassword()
        getQuote()

    }

    companion object {
        var userId: Int = 0
    }

    private fun getQuote() {
        // sign up button
        val sign_up_click = findViewById(R.id.SignUpNow) as TextView

        // URL to get a quote
        val url = "https://jt-insurance.vercel.app/quote"

        sign_up_click.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun animateLogo() {
        val logo: ImageView = findViewById(R.id.imageView6)

        logo.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.start()
    }

    private fun forgotPassword() {
        val forgot_pass_click = findViewById(R.id.forgotPwdTxt) as TextView

        forgot_pass_click.setOnClickListener {
            val i = Intent(this@LoginActivity, ForgotPwdActivity::class.java)
            startActivity(i)
        }
    }


    private fun login() {
        val sign_in_click: Button = findViewById(R.id.loginButton)

        sign_in_click.setOnClickListener {

            val email = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Please enter username", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener

            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Please enter password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            Toast.makeText(applicationContext, "Logging in...", Toast.LENGTH_LONG)
                .show()

            verifyApi()
        }
    }



    //
    private fun verifyApi() {
        val requestBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.23:8080/")
            .build()

        val insuranceRepository = requestBuilder.create(InsuranceRepository::class.java)
        val myCall: Call<List<User>> = insuranceRepository.getUsers()


        //
        myCall.enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val Users: List<User> = response.body()!!

                val email = binding.editTextUsername.text.toString().trim()
                val password = binding.editTextPassword.text.toString().trim()

                for (user in Users) {
                    if (user.email == email && user.password == password) {
                        userId = user.custid
                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(i)
                        Toast.makeText(applicationContext, "Welcome", Toast.LENGTH_SHORT).show()
                    }

                    if (user.email != email && user.password != password) {
                        Toast.makeText(applicationContext, "Incorrect credentials.", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

}


