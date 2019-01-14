package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Result {
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("first_air_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("poster_path")
    @Expose
    var imagePath: String? = null
}