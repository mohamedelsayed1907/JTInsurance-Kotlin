package com.example.JTInsurance.repository

import com.example.JTInsurance.LoginActivity.Companion.userId
import com.example.JTInsurance.models.HomePolicy
import com.example.JTInsurance.models.User
import com.example.JTInsurance.models.VehiclePolicy
import retrofit2.Call
import retrofit2.http.*

interface InsuranceRepository {

    @GET("customersall")
    fun getUsers(): Call<List<User>>

    @GET("homepolicies")
    fun getHomePolicies(): Call<List<HomePolicy>>

    @GET("vehiclepolicies")
    fun getAutoPolicies(): Call<List<VehiclePolicy>>
}


