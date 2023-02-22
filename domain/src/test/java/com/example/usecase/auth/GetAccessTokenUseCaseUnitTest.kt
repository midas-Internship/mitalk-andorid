package com.example.usecase.auth

import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetAccessTokenUseCaseUnitTest {
    private val authRepository = mock<AuthRepository>()
    private val getAccessTokenUseCase = GetAccessTokenUseCase(authRepository)

    @Test
    fun testLogin() {
        val entity = ""
        runBlocking {
            whenever(authRepository.getAccessToken()).thenReturn(entity)
            getAccessTokenUseCase().onSuccess { result ->
                Assert.assertEquals(result, entity)
            }
        }
    }
}