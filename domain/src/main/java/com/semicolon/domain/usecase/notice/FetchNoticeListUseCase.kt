package com.semicolon.domain.usecase.notice

import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.enums.NoticeType
import com.semicolon.domain.param.notice.FetchNoticeListParam
import com.semicolon.domain.repository.NoticeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchNoticeListUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository
) : UseCase<FetchNoticeListParam, Flow<NoticeEntity>>() {

    override suspend fun execute(data: FetchNoticeListParam): Flow<NoticeEntity> =
        noticeRepository.fetchNoticeList(data)
}