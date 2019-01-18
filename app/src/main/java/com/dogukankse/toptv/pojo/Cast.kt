package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Cast {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("profile_path")
    @Expose
    var imagePath: String? = null


}