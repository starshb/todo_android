package com.mtjin.envmate.data.model.response


import com.google.gson.annotations.SerializedName

data class IndustryEnergyRes(
    @SerializedName("media_url")
    val mediaUrl: String,
    @SerializedName("result")
    val result: List<List<String>>
)