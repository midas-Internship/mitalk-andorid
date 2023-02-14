package com.example.data.repository

import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.domain.entity.LoginEntity
import com.example.domain.param.LoginParam
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AuthRepositoryUnitTest {
    private val remoteAuthDataSource = mock<RemoteAuthDataSource>()
    private val authRepository = AuthRepositoryImpl(
        remoteAuthDataSource
    )

    @Test
    fun testLogin() {
        val param = mock<LoginParam>()
        val entity = mock<LoginEntity>()
        runBlocking {
            whenever(remoteAuthDataSource.login(param)).thenReturn(entity)
            val result = authRepository.login(param)
            assertEquals(entity, result)
        }
    }
}