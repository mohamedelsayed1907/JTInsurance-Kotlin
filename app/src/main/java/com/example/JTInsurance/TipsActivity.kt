package com.example.JTInsurance

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.JTInsurance.databinding.LoginMainBinding
import com.example.JTInsurance.databinding.VehicleTip1Binding

class TipsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicle_tip_1)
        tips()

    }

    private fun tips() {
        val getAnotherTip: Button = findViewById(R.id.getAnotherTip1)
        getAnotherTip.setOnClickListener {
            setContentView(R.layout.vehicle_tip_2)
        }
    }
}