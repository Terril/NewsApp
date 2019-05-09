package com.test.testnewsapp.testnewsapp.data.network

import com.test.testnewsapp.testnewsapp.data.network.model.response.NewsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitApi {

    @GET("/v2/everything")
    fun fetchAllLatestNews(@QueryMap params: Map<String, String>): Call<NewsApiResponse>
}
