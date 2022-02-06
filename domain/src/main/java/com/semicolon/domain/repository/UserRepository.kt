package com.semicolon.domain.repository

import com.semicolon.domain.entity.users.*
import com.semicolon.domain.param.user.*
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    )

    suspend fun postUserSignUp(
        postUserSignUpParam: PostUserSignUpParam
    )

    suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam
    ) : Flow<UserSignInEntity>

    suspend fun patchUserChangePassword(
        patchUserChangePasswordParam: PatchUserChangePasswordParam
    )

    suspend fun fetchUserProfile(
        userId: Int
    ) : Flow<UserProfileEntity>

    suspend fun fetchMyPage(
    ) : Flow<UserMyPageEntity>

    suspend fun fetchUserOwnBadge(
        userId: Int
    ) : Flow<UserOwnBadgeEntity>

    suspend fun setBadge(
        badgeId: Int
    )

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
        agencyCode: String
    )
}