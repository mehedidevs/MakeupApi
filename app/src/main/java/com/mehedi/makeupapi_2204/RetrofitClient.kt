package com.mehedi.makeupapi_2204

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://makeup-api.herokuapp.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ProductService::class.java)


}