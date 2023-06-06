package com.mehedi.makeupapi_2204

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {


    @GET("products.json")
    fun getAllProducts(): Call<List<ResponseProduct>>

    @GET("products/{pid}.json")
    fun getAllProductById(@Path("pid") pid: Int): Call<ResponseProduct>


}