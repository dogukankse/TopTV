package com.dogukankse.toptv.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class CastList {
    @SerializedName("cast")
    @Expose
    var castList: List<Cast>? = null
}