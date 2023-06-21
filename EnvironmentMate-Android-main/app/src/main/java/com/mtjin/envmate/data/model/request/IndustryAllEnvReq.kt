package com.mtjin.envmate.data.model.request


import com.google.gson.annotations.SerializedName

data class IndustryAllEnvReq(
    @SerializedName("media_url")
    val mediaUrl: String,
    @SerializedName("result")
    val result: String
)