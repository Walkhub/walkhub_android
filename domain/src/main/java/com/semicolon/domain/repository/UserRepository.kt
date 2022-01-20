package com.semicolon.domain.repository

import com.semicolon.domain.entity.users.UserMypageEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import com.semicolon.domain.entity.users.UserSignInEntity
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
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

    suspend fun fetchMypage(
    ) : Flow<UserMypageEntity>
}