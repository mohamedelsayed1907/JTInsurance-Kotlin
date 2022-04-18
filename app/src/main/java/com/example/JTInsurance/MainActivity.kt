package com.example.JTInsurance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.JTInsurance.LoginActivity.Companion.userId
import com.example.JTInsurance.repository.InsuranceRepository
import com.example.JTInsurance.databinding.ActivityMainBinding
import com.example.JTInsurance.models.User
import com.google.android.material.navigation.NavigationView
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity: AppCompatActivity() {


    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    lateinit var imageViewCloud: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hamburgerMenu()
        userTitle()
        tipsButton()
        getAnotherQuote()
        getHomeInsurance()
        getAutoInsurance()

    }

    private fun tipsButton() {
        val tipsBtn: TextView = findViewById(com.example.JTInsurance.R.id.tipsSavingMoney)

        tipsBtn.setOnClickListener {
            val i = Intent(this@MainActivity, TipsActivity::class.java)
            startActivity(i)
        }
    }

    private fun getHomeInsurance() {
        val insuranceBtn: TextView = findViewById(com.example.JTInsurance.R.id.viewHomeInsurance)

        insuranceBtn.setOnClickListener {
            val i = Intent(this@MainActivity, HomePolicyActivity::class.java)
            startActivity(i)
        }
    }

    private fun getAutoInsurance() {
        val autoInsuranceBtn: TextView = findViewById(com.example.JTInsurance.R.id.viewAutoInsurance)

        autoInsuranceBtn.setOnClickListener {
            val i = Intent(this@MainActivity, AutoPolicyActivity::class.java)
            startActivity(i)
        }
    }

    private fun hamburgerMenu() {
        val drawerLayout: DrawerLayout = findViewById(com.example.JTInsurance.R.id.drawerLayout)
        val navView: NavigationView = findViewById(com.example.JTInsurance.R.id.nav_view)

        val burger: ImageView = findViewById(com.example.JTInsurance.R.id.hamburgerMenu)

        toggle = ActionBarDrawerToggle(this, drawerLayout, com.example.JTInsurance.R.string.open, com.example.JTInsurance.R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                com.example.JTInsurance.R.id.logOut ->
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))

                com.example.JTInsurance.R.id.contactus ->
                    startActivity(Intent(this@MainActivity, ContactUsActivity::class.java))

                com.example.JTInsurance.R.id.myProfile ->
                    startActivity(Intent(this@MainActivity, CustomerInfoActivity::class.java))

            }
            true
        }
    }

    private fun userTitle() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.23:8080/")
            .build()

        val jsonPlaceHolderApi = retrofit.create(InsuranceRepository::class.java)
        val myCall: Call<List<User>> = jsonPlaceHolderApi.getUsers()

        myCall.enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                val Users: List<User> = response.body()!!
                val firstName: TextView = findViewById(com.example.JTInsurance.R.id.firstName)
                val email: TextView = findViewById(com.example.JTInsurance.R.id.userEmail)

                for (user in Users) {
                    if (user.custid == userId) {
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

    private fun getAnotherQuote() {
            // sign up button
            val get_another_quote = findViewById(com.example.JTInsurance.R.id.getAnotherQuote) as TextView

            // URL to get a quote
            val url = "https://jt-insurance.vercel.app/quote"

            get_another_quote.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
