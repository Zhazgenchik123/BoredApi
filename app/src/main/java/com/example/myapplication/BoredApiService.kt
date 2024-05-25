package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
interface BoredApiService {
    @GET("activity")
    fun getActivity(): Call<ActivityResponse>
}