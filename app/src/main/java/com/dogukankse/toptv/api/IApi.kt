package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Genres
import com.dogukankse.toptv.pojo.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApi {

    @GET("tv/popular") //?api_key=91e9cdf4944e3b00f2e29053d1e692de&language=en-US&page=1")
    fun getPopulerShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<Repo>

    @GET("genre/tv/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Genres>
}