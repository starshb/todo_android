package com.mtjin.envmate.data.model.request


import com.google.gson.annotations.SerializedName

data class IndustrySameAllEnvReq(
    @SerializedName("industry")
    val industry: String,
    @SerializedName("usage")
    val usage: Int,
    @SerializedName("year")
    val year: Int
)