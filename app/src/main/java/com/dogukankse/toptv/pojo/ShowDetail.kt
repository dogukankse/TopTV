package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ShowDetail {

    @SerializedName("genres")
    @Expose
    val genreList: List<Genre>? = null

    @SerializedName("overview")
    @Expose
    val overview: String? = null

    @SerializedName("name")
    @Expose
    var title: String? = null

    @SerializedName("vote_average")
    @Expose
    var vote: Float? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("number_of_episodes")
    @Expose
    var numberOfEpisodes: Int? = null

    @SerializedName("number_of_seasons")
    @Expose
    var numberOfSeasons: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("poster_path")
    @Expose
    var imagePath: String? = null
}