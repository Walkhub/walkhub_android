package com.semicolon.domain.usecase.notice

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.domain.repository.NoticeRepository
import org.junit.Test

class FetchNoticeListUseCaseUnitTest {

    private val noticeRepository = mock<NoticeRepository>()

    private val fetchNoticeListUseCase = FetchNoticeListUseCase(noticeRepository)

    @Test
    fun testFetchNoticeListUseCase() {
    }
}