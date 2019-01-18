package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Show


interface OnGetShowListCallback {

    fun onSuccess(page: Int, shows: MutableList<Show>)

    fun onError()
}