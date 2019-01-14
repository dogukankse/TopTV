package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Result


interface OnGetShowsCallback {

    fun onSuccess(page: Int, shows: MutableList<Result>)

    fun onError()
}