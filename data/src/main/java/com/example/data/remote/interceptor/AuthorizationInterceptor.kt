package com.example.data.remote.interceptor

import com.example.data.BuildConfig
import com.example.data.local.AuthPreference
import com.example.data.remote.response.LoginResponse
import com.example.domain.exception.NeedLoginException
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val authPreference: AuthPreference
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath
        val ignorePath = listOf(
            "/customer/signin"
        )
        if (ignorePath.contains(path)) return chain.proceed(request)

        val expiredAt = runBlocking { authPreference.fetchExpirationAt() }
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        if (expiredAt.isBefore(currentTime)) {
            val client = OkHttpClient()
            val refreshToken = runBlocking { authPreference.fetchRefreshToken() }

            val tokenRefreshRequest = Request.Builder()
                .url(BuildConfig.BASE_URL)
                .put("".toRequestBody("application/json".toMediaTypeOrNull()))
                .addHeader("Refresh-Token", "Bearer $refreshToken")
                .build()
            val response = client.newCall(tokenRefreshRequest).execute()

            if (response.isSuccessful) {
                val token = Gson().fromJson(
                    response.body!!.toString(),
                    LoginResponse::class.java
                )
                runBlocking {
                    authPreference.saveAccessToken(token.accessToken)
                    authPreference.saveRefreshToken(token.refreshToken)
                    authPreference.saveExpirationAt(LocalDateTime.parse(token.expirationAt))
                }
            } else throw NeedLoginException()
        }

        val accessToken = runBlocking { authPreference.fetchAccessToken() }
        return chain.proceed(
            request.newBuilder().addHeader(
                "Authorization",
                "Bearer $accessToken"
            ).build()
        )
    }
}