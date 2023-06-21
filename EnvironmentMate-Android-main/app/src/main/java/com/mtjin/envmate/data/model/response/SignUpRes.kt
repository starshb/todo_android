package com.mtjin.envmate.data.model.response

import com.google.gson.annotations.SerializedName

data class SignUpRes(
    @SerializedName("message")
    val message: String
)