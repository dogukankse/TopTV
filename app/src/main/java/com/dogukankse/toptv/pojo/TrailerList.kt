package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrailerList {

    @SerializedName("results")
    @Expose
    val trailerList: List<Trailer>? = null
}