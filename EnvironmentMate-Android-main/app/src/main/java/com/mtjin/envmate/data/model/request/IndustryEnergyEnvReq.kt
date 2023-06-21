package com.mtjin.envmate.data.model.request


import com.google.gson.annotations.SerializedName

data class IndustryEnergyEnvReq(
    @SerializedName("coal")
    val coal: Int,
    @SerializedName("electric")
    val electric: Int,
    @SerializedName("gas")
    val gas: Int,
    @SerializedName("industry")
    val industry: String,
    @SerializedName("oil")
    val oil: Int,
    @SerializedName("other")
    val other: Int,
    @SerializedName("thermal")
    val thermal: Int,
    @SerializedName("year")
    val year: Int
)