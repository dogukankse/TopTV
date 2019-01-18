package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface IApi {

    @GET("tv/popular")
    fun getShowList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ShowList>

    @GET("genre/tv/list")
    fun getGenreList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<GenreList>

    @GET("tv/{tv_id}")
    fun getShow(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ShowDetail>

    @GET("tv/{tv_id}/credits")
    fun getCastList(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<CastList>

    @GET("tv/{tv_id}/recommendations")
    fun getRecommendationList(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<RecommendationList>

    @GET("tv/{tv_id}/videos")
    fun getTrailerList(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKEy: String
    ): Call<TrailerList>

}