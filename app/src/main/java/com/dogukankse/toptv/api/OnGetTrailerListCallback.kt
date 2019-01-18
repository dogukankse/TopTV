package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Trailer

interface OnGetTrailerListCallback {
    fun onSuccess(trailerList: List<Trailer>)

    fun onError()
}