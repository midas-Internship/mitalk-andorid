package com.example.data.remote.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class CheckReviewStateResponse(
    @SerializedName("need_review") val counsellorId: UUID? = null,
)
