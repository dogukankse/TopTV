package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Genres {

    @SerializedName("genres")
    @Expose
    val genres:List<Genre>?=null
}