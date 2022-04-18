package com.example.JTInsurance.models

import java.util.*

data class VehiclePolicy(
    val id: Int,
    val type: String,
    val startDate: Date,
    val endDate: Date,
    val premium: Float,
    val active: Boolean

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HomePolicy

        if (id != other.id) return false
        if (type != other.type) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false
        if (premium != other.premium) return false
        if (active != other.active) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + type.hashCode()
        result = 31 * result + startDate.hashCode()
        result = 31 * result + endDate.hashCode()
        result = 31 * result + premium.hashCode()
        result = 31 * result + active.hashCode()
        return result
    }
}