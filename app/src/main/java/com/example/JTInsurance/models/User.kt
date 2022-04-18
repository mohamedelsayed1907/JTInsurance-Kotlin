package com.example.JTInsurance.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    var custid: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
    val dob: Date,
    val address: String,
    val city: String,
    val province: String,
    val postalCode: String,
    val accidentAmt: Int,
    val homeQuotes: Array<HomeQuotes>,
    val vehicleQuotes: Array<VehicleQuotes>,
    val homePolicy: String,
    val vehiclePolicy: String
) {
}