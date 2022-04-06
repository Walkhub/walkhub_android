package com.semicolon.domain.repository

import com.semicolon.domain.entity.users.*
import com.semicolon.domain.param.user.*
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun verifyUserPhoneNumber(
        phone_number: Int
    )

    suspend fun postUserSignUp(
        postUserSignUpParam: PostUserSignUpParam
    )

    suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam
    )

    suspend fun patchUserChangePassword(
        patchUserChangePasswordParam: PatchUserChangePasswordParam
    )

    suspend fun fetchUserProfile(
        userId: Int
    ) : Flow<UserProfileEntity>

    suspend fun fetchMyPage(
    ) : Flow<UserMyPageEntity>

    suspend fun updateProfile(
        updateProfileParam: UpdateProfileParam
    )

    suspend fun findUserAccount(
        phoneNumber: String
    ) : Flow<FindUserAccountEntity>

    suspend fun patchUserHealth(
        patchUserHealthParam: PatchUserHealthParam
    )

    suspend fun signUpClass(
        signUpClassParam: SignUpClassParam
    )

    suspend fun patchSchool(
        schoolId: Int
    )

    suspend fun autoLogin()

    suspend fun patchDailyWalkGoal(
        patchDailyWalkGoalParam: PatchDailyWalkGoalParam
    )

    suspend fun fetchCaloriesLevel() : Flow<FetchCaloriesLevelEntity>

    suspend fun deleteAccount()

    suspend fun deleteClass()

    suspend fun checkAccountOverlap(accountId: String)

    suspend fun checkClassCode(code: String)

    suspend fun changeIndependence(userId: Int)

    suspend fun fetchDailyGoal(): Flow<FetchDailyGoalEntity>

    suspend fun fetchInfo(): Flow<FetchInfoEntity>

    suspend fun fetchUserHealth(): Flow<FetchUserHealthEntity>

    suspend fun fetchAuthInfo(): Flow<FetchAuthInfoEntity>
}