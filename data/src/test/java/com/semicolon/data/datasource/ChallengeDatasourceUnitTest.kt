package com.semicolon.data.datasource

import com.semicolon.data.datasource.challenge.local.ChallengeLocalDataSource
import com.semicolon.data.datasource.challenge.remote.ChallengeRemoteDateSource
import com.semicolon.data.local.database.challenge.ChallengeDao
import com.semicolon.data.remote.api.ChallengeApi
import org.junit.Before
import org.mockito.Mock

class ChallengeDatasourceUnitTest {
    @Mock
    private lateinit var challengeApi: ChallengeApi

    @Mock
    private lateinit var challengeDao: ChallengeDao

    private lateinit var challengeRemoteDataSource: ChallengeRemoteDateSource

    private lateinit var challengeLocalDataSource: ChallengeLocalDataSource

    @Before
    fun init() {

    }
}