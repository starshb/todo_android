package com.mtjin.envmate.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginRes(
    @SerializedName("key")
    val key: String
)