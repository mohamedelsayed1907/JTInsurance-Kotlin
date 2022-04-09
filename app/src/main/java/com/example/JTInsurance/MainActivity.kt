package com.example.JTInsurance

import android.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.JTInsurance.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity: AppCompatActivity() {


    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    lateinit var imageViewCloud: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // drawer layout for hamburger menu
        val drawerLayout: DrawerLayout = findViewById(com.example.JTInsurance.R.id.drawerLayout)

        // navigation view for hamburger menu
        val navView: NavigationView = findViewById(com.example.JTInsurance.R.id.nav_view)


        val tipsBtn: TextView = findViewById(com.example.JTInsurance.R.id.tipsSavingMoney)

        //hamburger menu icon
        val burger: ImageView = findViewById(com.example.JTInsurance.R.id.hamburgerMenu)

        // tips button on click listener
        tipsBtn.setOnClickListener {
            val i = Intent(this@MainActivity, TipsActivity::class.java)
            startActivity(i)
        }

        // hamburger menu functionality
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}
