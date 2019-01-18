package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.*
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

    fun getTrailerList(showId: Int, callback: OnGetTrailerListCallback) {
        api.getTrailerList(showId, apiKey)
            .enqueue(object : Callback<TrailerList> {
                override fun onFailure(call: Call<TrailerList>, t: Throwable) {
                    callback.onError()
                }

                override fun onResponse(call: Call<TrailerList>, response: Response<TrailerList>) {
                    if (response.isSuccessful) {
                        val trailerList = response.body()
                        if (trailerList?.trailerList != null) {
                            callback.onSuccess(trailerList.trailerList!!)
                        } else callback.onError()
                    } else callback.onError()
                }

            })
    }

    fun getRecommendationList(showId: Int, callback: OnGetRecommendationListCallback) {
        api.getRecommendationList(showId, apiKey)
            .enqueue(object : Callback<RecommendationList> {
                override fun onFailure(call: Call<RecommendationList>, t: Throwable) {
                    callback.onError()
                }

                override fun onResponse(call: Call<RecommendationList>, response: Response<RecommendationList>) {
                    if (response.isSuccessful) {
                        val recList = response.body()
                        if (recList?.showList != null) {
                            callback.onSuccess(recList.showList!!)
                        } else callback.onError()
                    } else callback.onError()
                }

            })
    }

    fun getShowList(page: Int, callback: OnGetShowListCallback) {
        api.getShowList(apiKey, lang, page)
            .enqueue(object : Callback<ShowList> {
                override fun onResponse(call: Call<ShowList>, response: Response<ShowList>) {
                    if (response.isSuccessful) {
                        val res = response.body()
                        if (res?.showList != null) {
                            callback.onSuccess(res.page!!, res.showList!!)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<ShowList>, t: Throwable) {
                    callback.onError()
                }
            })
    }

    fun getCastList(showId: Int, callback: OnGetCastCallback) {
        api.getCastList(showId, apiKey, lang).enqueue(object : Callback<CastList> {
            override fun onFailure(call: Call<CastList>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<CastList>, response: Response<CastList>) {
                if (response.isSuccessful) {
                    val castList = response.body()
                    if (castList!!.castList != null) {
                        callback.onSuccess(castList.castList!!)
                    } else {
                        callback.onError()
                    }
                } else {
                    callback.onError()
                }
            }
        })
    }

    fun getGenreList(callback: OnGetGenreListCallback) {
        api.getGenreList(apiKey, lang).enqueue(object : Callback<GenreList> {
            override fun onFailure(call: Call<GenreList>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<GenreList>, response: Response<GenreList>) {
                if (response.isSuccessful) {
                    val genreList = response.body()
                    if (genreList?.genreList != null) {
                        callback.onSuccess(genreList.genreList)
                    } else {
                        callback.onError()
                    }
                } else {
                    callback.onError()
                }
            }

        })
    }

    fun getShow(showId: Int, callback: OnGetShowCallback) {
        api.getShow(showId, apiKey, lang)
            .enqueue(object : Callback<ShowDetail> {
                override fun onResponse(call: Call<ShowDetail>, response: Response<ShowDetail>) {
                    if (response.isSuccessful) {
                        val show = response.body()
                        if (show != null) {
                            callback.onSuccess(show)
                        } else {
                            callback.onError()
                        }
                    } else {
                        callback.onError()
                    }
                }

                override fun onFailure(call: Call<ShowDetail>, t: Throwable) {
                    callback.onError()
                }
            })
    }


}