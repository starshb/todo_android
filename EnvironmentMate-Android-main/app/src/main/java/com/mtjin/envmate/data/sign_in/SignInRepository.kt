package com.mtjin.envmate.data.sign_in

import com.mtjin.envmate.data.model.request.LoginReq
import com.mtjin.envmate.data.model.response.LoginRes
import io.reactivex.Single

interface SignInRepository {
    fun requestLogin(loginReq: LoginReq): Single<LoginRes>
}