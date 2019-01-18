package com.dogukankse.toptv.api

import com.dogukankse.toptv.pojo.Show

interface OnGetRecommendationListCallback {
    fun onSuccess(recommendationList: List<Show>)

    fun onError()
}