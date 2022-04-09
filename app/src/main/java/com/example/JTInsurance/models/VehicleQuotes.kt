package com.example.JTInsurance.models

import java.util.*

data class VehicleQuotes(
    val id: Int,
    val startDate: Date,
    val endDate: Date,
    val premium: Float
)