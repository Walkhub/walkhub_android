package com.semicolon.domain.usecase.notice

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.domain.repository.NoticeRepository

class FetchNoticeListUseCaseUnitTest {

    private val noticeRepository = mock<NoticeRepository>()

    private val fetchNoticeListUseCase = FetchNoticeListUseCase(noticeRepository)
}