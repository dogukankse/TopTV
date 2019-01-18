package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Genre

interface OnGetGenreListCallback {

    fun onSuccess(genres: List<Genre>)

    fun onError()
}