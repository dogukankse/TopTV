package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.ShowDetail

interface OnGetShowCallback {
    fun onSuccess(showDetail: ShowDetail)

    fun onError()
}