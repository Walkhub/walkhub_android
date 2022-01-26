package com.semicolon.domain.usecase.notice

import com.semicolon.domain.entity.notice.NoticeDetailEntity
import com.semicolon.domain.repository.NoticeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNoticeDetailUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository
) : UseCase<Int, Flow<NoticeDetailEntity>>() {

    override suspend fun execute(data: Int): Flow<NoticeDetailEntity> =
        noticeRepository.loadNoticeDetail(data)
}