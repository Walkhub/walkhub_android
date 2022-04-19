package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalSchoolDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteSchoolDataSource
import com.semicolon.data.remote.response.school.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.data.util.toMultipart
import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.entity.school.SearchSchoolEntity
import com.semicolon.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val remoteImagesDataSource: RemoteImagesDataSource,
    private val localSchoolDataSource: LocalSchoolDataSource,
    private val remoteSchoolDataSource: RemoteSchoolDataSource
) : SchoolRepository {

    override suspend fun setSchoolLogo(profileImage: File) {
        val imageUrl = remoteImagesDataSource.postImages(
            listOf(profileImage.toMultipart())
        ).imageUrl.first()

        remoteSchoolDataSource.setSchoolLogo(imageUrl)
    }

    override suspend fun fetchSchoolDetail(schoolId: Int): Flow<SchoolDetailEntity> =
        OfflineCacheUtil<SchoolDetailEntity>()
            .remoteData { remoteSchoolDataSource.fetchSchoolDetail(schoolId) }
            .localData { localSchoolDataSource.fetchSchoolDetail() }
            .doOnNeedRefresh { localSchoolDataSource.insertSchoolDetail(it) }
            .createFlow()

    override suspend fun searchSchool(name: String): Flow<SearchSchoolEntity> =
        flow { emit(remoteSchoolDataSource.searchSchool(name).toEntity()) }
}