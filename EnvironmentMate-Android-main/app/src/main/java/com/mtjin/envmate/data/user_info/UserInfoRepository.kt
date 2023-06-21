package com.mtjin.envmate.data.user_info

import com.mtjin.envmate.data.model.request.User
import com.mtjin.envmate.data.model.response.SignUpRes
import io.reactivex.Single

interface UserInfoRepository {
    fun insertUserInfo(user: User): Single<SignUpRes>
    fun requestSignUpAccept(email: String): Single<SignUpRes>
}