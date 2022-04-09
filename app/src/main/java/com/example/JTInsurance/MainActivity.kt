package com.example.JTInsurance

import android.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.JTInsurance.LoginActivity.Companion.userId
import com.example.JTInsurance.api.Api
import com.example.JTInsurance.databinding.ActivityMainBinding
import com.example.JTInsurance.databinding.LoginMainBinding
import com.example.JTInsurance.models.User
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.Files.find


class MainActivity: AppCompatActivity() {


    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    lateinit var imageViewCloud: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hamburgerMenu()
        title()

    }



    private fun hamburgerMenu() {
        val drawerLayout: DrawerLayout = findViewById(com.example.JTInsurance.R.id.drawerLayout)
        val navView: NavigationView = findViewById(com.example.JTInsurance.R.id.nav_view)
        val tipsBtn: TextView = findViewById(com.example.JTInsurance.R.id.tipsSavingMoney)
        val burger: ImageView = findViewById(com.example.JTInsurance.R.id.hamburgerMenu)

        tipsBtn.setOnClickListener {
            val i = Intent(this@MainActivity, TipsActivity::class.java)
            startActivity(i)
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, com.example.JTInsurance.R.string.open, com.example.JTInsurance.R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                com.example.JTInsurance.R.id.home -> Toast.makeText(
                    applicationContext,
                    "Clicked home",
                    Toast.LENGTH_SHORT
                )
                    .show()

                com.example.JTInsurance.R.id.logOut ->
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
            true
        }
    }

    private fun title() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.23:8080/")
            .build()

        val jsonPlaceHolderApi = retrofit.create(Api::class.java)
        val myCall: Call<List<User>> = jsonPlaceHolderApi.getUsers()

        myCall.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                val Users: List<User> = response.body()!!
                val firstName: TextView = findViewById(com.example.JTInsurance.R.id.firstName)
                val email: TextView = findViewById(com.example.JTInsurance.R.id.userEmail)

                for (user in Users) {
                    if (user.id == userId) {
                        firstName.text = user.firstName
                        email.text = user.email
                    }
                }

            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
