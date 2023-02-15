package com.example.data.remote.request

import com.example.domain.param.LoginParam
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_img")
    val profileImg: String,
)

fun LoginParam.toRequest() = LoginRequest(
    email = email,
    name = name,
    profileImg = profileImg
)