package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Cast

interface OnGetCastCallback {
    fun onSuccess(castList: List<Cast>)
    fun onError()
}