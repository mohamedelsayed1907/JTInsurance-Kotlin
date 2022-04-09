package com.example.JTInsurance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.JTInsurance.api.Api
import com.example.JTInsurance.databinding.LoginMainBinding
import com.example.JTInsurance.models.LoginResponse
import com.example.JTInsurance.models.User
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import okhttp3.ResponseBody
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


        // sign up button
        val sign_up_click = findViewById(R.id.SignUpNow) as TextView

        // forgot password button
        val forgot_pass_click = findViewById(R.id.forgotPwdTxt) as TextView

        // sign in button
        val sign_in_click: Button = findViewById(R.id.loginButton)

        // logo
        val logo: ImageView = findViewById(R.id.imageView6)

        // URL to get a quote
        val url = "https://jt-insurance.vercel.app/quote"

        // logo animation
        logo.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.start()

        // sign up on click listener
        sign_up_click.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        // forgot password on click listener
        forgot_pass_click.setOnClickListener {
            val i = Intent(this@LoginActivity, ForgotPwdActivity::class.java)
            startActivity(i)
        }
        

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

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.23:8080/")
                .build()

            val jsonPlaceHolderApi = retrofit.create(Api::class.java)
            val myCall: Call<List<User>> = jsonPlaceHolderApi.getUsers()

            myCall.enqueue(object: Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val Users: List<User> = response.body()!!
                    val stringBuilder = StringBuilder()
                    var isLoggedIn: Boolean = true


                    for (user in Users) {
                        if (user.email == email && user.password == password) {
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

            /*else {
                val i = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(i)
            }*/
        }
    }


}
