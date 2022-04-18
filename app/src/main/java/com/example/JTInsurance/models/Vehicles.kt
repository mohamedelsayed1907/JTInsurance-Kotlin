package com.example.JTInsurance.models

import java.util.*

data class Vehicles(
    val vin: String,
    val make: String,
    val model: String,
    val plateNum: String,
    val color: String,
    val year: Int,
    val carValue: Float,
    val purchaseDate: Date,
    val vehicleQuotes: Array<VehicleQuotes>

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vehicles

        if (vin != other.vin) return false
        if (make != other.make) return false
        if (model != other.model) return false
        if (plateNum != other.plateNum) return false
        if (color != other.color) return false
        if (year != other.year) return false
        if (carValue != other.carValue) return false
        if (purchaseDate != other.purchaseDate) return false
        if (!vehicleQuotes.contentEquals(other.vehicleQuotes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = vin.hashCode()
        result = 31 * result + make.hashCode()
        result = 31 * result + model.hashCode()
        result = 31 * result + plateNum.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + year
        result = 31 * result + carValue.hashCode()
        result = 31 * result + purchaseDate.hashCode()
        result = 31 * result + vehicleQuotes.contentHashCode()
        return result
    }
}