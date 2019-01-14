package com.dogukankse.toptv.api

import android.util.Log
import com.dogukankse.toptv.pojo.Genres
import com.dogukankse.toptv.pojo.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient private constructor(private val api: IApi) {

    companion object {

        private const val baseUrl = "https://api.themoviedb.org/3/"
        private const val lang = "en-US"
        private const val apiKey = "91e9cdf4944e3b00f2e29053d1e692de"

        private var client: ApiClient? = null

        val instance: ApiClient
            get() {
                if (client == null) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    client = ApiClient(retrofit.create(IApi::class.java))
                }

                return client!!
            }
    }

    fun getShows(page: Int, callback: OnGetShowsCallback) {
        Log.d("MoviesRepository", "Next Page = $page")
        api.getPopulerShows(apiKey, lang, page)
            .enqueue(object : Callback<Repo> {
                override fun onResponse(call: Call<Repo>, response: Response<Repo>) {
                    if (response.isSuccessful) {
                        val res = response.body()
                        if (res?.results != null) {
                            callback.onSuccess(res.page!!,res.results!!)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<Repo>, t: Throwable) {
                    callback.onError()
                }
            })
    }

    fun getGenres(callback: OnGetGenresCallback) {
        api.getGenres(apiKey, lang).enqueue(object : Callback<Genres> {
            override fun onFailure(call: Call<Genres>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                if (response.isSuccessful) {
                    val genres = response.body()
                    if (genres?.genres != null) {
                        callback.onSuccess(genres.genres)
                    } else {
                        callback.onError()
                    }
                } else {
                    callback.onError()
                }
            }

        })
    }


}