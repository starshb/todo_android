package com.mtjin.envmate.data.user_info

import com.mtjin.envmate.api.ApiInterface
import com.mtjin.envmate.data.model.request.User
import com.mtjin.envmate.data.model.response.SignUpRes
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    UserInfoRepository {
    override fun insertUserInfo(user: User): Single<SignUpRes> {
        return apiInterface.insertUserInfo(
            user.businessName,
            user.businessNumber,
            user.officerEmail,
            user.officerName,
            user.officerPhone,
            user.officerPosition,
            user.password,
            user.industry,
            user.locationName
        )
    }

    override fun requestSignUpAccept(email: String): Single<SignUpRes> {
        return apiInterface.requestSignUpAccept(email)
    }
}