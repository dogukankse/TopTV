package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RecommendationList {

    @SerializedName("results")
    @Expose
    var showList: List<Show>? = null

}