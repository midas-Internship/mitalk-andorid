package com.example.usecase.auth

import com.example.domain.entity.LoginEntity
import com.example.domain.param.LoginParam
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.auth.LoginUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoginUseCaseUnitTest {
    private val authRepository = mock<AuthRepository>()
    private val loginUseCase = LoginUseCase(authRepository)

    @Test
    fun testLogin() {
        val param = mock<LoginParam>()
        val entity = mock<LoginEntity>()
        runBlocking {
            whenever(authRepository.login(param)).thenReturn(entity)
            loginUseCase(param).onSuccess { result ->
                assertEquals(result, entity)
            }
        }
    }
}