package com.example.data.datasource

import com.example.data.remote.api.AuthApi
import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.data.remote.datasource.RemoteAuthDataSourceImpl
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.toEntity
import com.example.domain.param.LoginParam
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.ZonedDateTime
import java.util.*

class RemoteAuthDataSourceUnitTest {
    private val authApi = mock<AuthApi>()
    private val remoteAuthDataSource: RemoteAuthDataSource = RemoteAuthDataSourceImpl(authApi)

    @Test
    fun testLogin() {
        val param = LoginParam(
            email = "abcd1234@naver.com",
            name = "홍길동",
            profileImg = "프로필 이미지"
        )
        val response = LoginResponse(
            accessToken = "",
            refreshToken = "",
            accessExp = ZonedDateTime.now().toString(),
            refreshExp = ZonedDateTime.now().toString()
        )
        runBlocking {
            whenever(authApi.login(param.toRequest())).thenReturn(response)
            val result = remoteAuthDataSource.login(param)
            assertEquals(response.toEntity(), result)
        }
    }
}