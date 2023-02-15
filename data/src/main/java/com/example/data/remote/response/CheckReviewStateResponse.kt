package com.example.data.remote.response

import com.example.domain.entity.CheckReviewStateEntity
import com.google.gson.annotations.SerializedName
import java.util.*

data class CheckReviewStateResponse(
    @SerializedName("need_review") val counsellorId: UUID? = null,
    @SerializedName("name") val name: String? = null
)

internal fun CheckReviewStateResponse.toEntity() =
    CheckReviewStateEntity(
        counsellorId = counsellorId,
        name = name,
    )