/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mtjin.envmate.api

import com.mtjin.envmate.data.model.response.LoginRes
import com.mtjin.envmate.data.model.response.SignUpRes
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiInterface {

    @FormUrlEncoded
    @POST("accounts/signup-req")
    fun insertUserInfo(
        @Field("business_name") businessName: String,
        @Field("business_number")
        businessNumber: String,
        @Field("officer_email")
        officerEmail: String,
        @Field("officer_name")
        officerName: String,
        @Field("officer_phone")
        officerPhone: String,
        @Field("officer_position")
        officerPosition: String,
        @Field("password")
        password: String,
        @Field("industry")
        industry: String,
        @Field("location_name")
        locationName: String
    ): Single<SignUpRes>

    @FormUrlEncoded
    @POST("accounts/signup-accept")
    fun requestSignUpAccept(
        @Field("officer_email") officeEmail: String
    ): Single<SignUpRes>

    @FormUrlEncoded
    @POST("accounts/login/")
    fun requestLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Single<LoginRes>


    companion object {
        private const val BASE_URL =
            "http://ff4839aab5bb.ngrok.io"

        fun create(): ApiInterface {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}
