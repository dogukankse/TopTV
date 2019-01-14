package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Genre

interface OnGetGenresCallback {

    fun onSuccess(genres: List<Genre>)

    fun onError()
}