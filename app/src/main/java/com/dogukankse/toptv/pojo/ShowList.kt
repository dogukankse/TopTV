package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ShowList{
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var showList: MutableList<Show>? = null
}