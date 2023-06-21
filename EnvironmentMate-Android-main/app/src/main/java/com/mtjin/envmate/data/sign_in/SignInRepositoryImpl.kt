package com.mtjin.envmate.data.sign_in

import com.mtjin.envmate.api.ApiInterface
import com.mtjin.envmate.data.model.request.LoginReq
import com.mtjin.envmate.data.model.response.LoginRes
import io.reactivex.Single
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    SignInRepository {
    override fun requestLogin(loginReq: LoginReq): Single<LoginRes> {
        return apiInterface.requestLogin(loginReq.email, loginReq.password)
    }
}