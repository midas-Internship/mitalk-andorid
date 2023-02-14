package com.example.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(

): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        val method = request.method()
        val ignorePath = listOf(
            "login"
        )
        if (ignorePath.contains(path)) return chain.proceed(request)

        val

    }
}