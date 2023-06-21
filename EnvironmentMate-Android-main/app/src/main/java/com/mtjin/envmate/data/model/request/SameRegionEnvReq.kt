package com.mtjin.envmate.data.model.request


import com.google.gson.annotations.SerializedName

data class SameRegionEnvReq(
    @SerializedName("location_name")
    val locationName: String,
    @SerializedName("usage")
    val usage: Int,
    @SerializedName("year")
    val year: Int
)