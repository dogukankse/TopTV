package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreList {

    @SerializedName("genres")
    @Expose
    val genreList:List<Genre>?=null
}