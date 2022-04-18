package com.example.JTInsurance.models

data class Homes(
    val homeid: Int,
    val houseVal: Float,
    val address: String,
    val city: String,
    val province: String,
    val heatingType: String,
    val location: String,
    val homeQuotes: Array<HomeQuotes>,
    val homePolicy: Array<HomePolicy>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Homes

        if (homeid != other.homeid) return false
        if (houseVal != other.houseVal) return false
        if (address != other.address) return false
        if (city != other.city) return false
        if (province != other.province) return false
        if (heatingType != other.heatingType) return false
        if (location != other.location) return false
        if (!homeQuotes.contentEquals(other.homeQuotes)) return false
        if (!homePolicy.contentEquals(other.homePolicy)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = homeid
        result = 31 * result + houseVal.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + province.hashCode()
        result = 31 * result + heatingType.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + homeQuotes.contentHashCode()
        result = 31 * result + homePolicy.contentHashCode()
        return result
    }
}