package com.example.data.remote.request

import com.example.domain.param.ReviewParam
import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("star")
    val star: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("review_item")
    val reviewItem: List<String>
)

fun ReviewParam.toRequest() = ReviewRequest(
    star = star,
    message = message,
    reviewItem = reviewItem
)