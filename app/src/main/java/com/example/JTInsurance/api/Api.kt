package com.example.JTInsurance.api

import com.example.JTInsurance.models.LoginResponse
import com.example.JTInsurance.models.User
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("customers")
    fun getUsers(): Call<List<User>>

}


