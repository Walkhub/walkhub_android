package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepositoryUnitTest {

    private val localUserDataSource = mock<LocalUserDataSource>()
    private val remoteUserDataSource = mock<RemoteUserDataSource>()

    private val imageDataSource = mock<RemoteImagesDataSource>()

    private val userRepository =
        UserRepositoryImpl(imageDataSource, localUserDataSource, remoteUserDataSource)


}