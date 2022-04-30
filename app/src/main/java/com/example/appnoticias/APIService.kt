package com.example.appnoticias

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getMostPopular(@Url url:String): Response<NYTResponse>
}