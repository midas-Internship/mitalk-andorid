package com.example.data.datasource

import com.example.data.remote.api.ReviewApi
import com.example.data.remote.datasource.RemoteReviewDataSource
import com.example.data.remote.datasource.RemoteReviewDataSourceImpl
import com.example.data.remote.request.toRequest
import com.example.domain.param.ReviewParam
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteReviewDataSourceUnitTest {
    private val reviewApi = mock<ReviewApi>()
    private val remoteReviewDataSource: RemoteReviewDataSource =
        RemoteReviewDataSourceImpl(reviewApi)

    @Test
    fun testPostReview() {
        val param = ReviewParam(
            star = 2,
            message = null,
            reviewItem = listOf(),
            counsellorId = null
        )
        runBlocking {
            whenever(reviewApi.postReview(param.toRequest())).thenReturn(Unit)
            val result = remoteReviewDataSource.postReview(param)
            assertEquals(Unit, result)
        }
    }
}