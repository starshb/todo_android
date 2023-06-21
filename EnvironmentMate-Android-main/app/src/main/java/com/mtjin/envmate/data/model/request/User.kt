package com.mtjin.envmate.data.model.request


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("business_name")
    val businessName: String,
    @SerializedName("business_number")
    val businessNumber: String,
    @SerializedName("officer_email")
    val officerEmail: String,
    @SerializedName("officer_name")
    val officerName: String,
    @SerializedName("officer_phone")
    val officerPhone: String,
    @SerializedName("officer_position")
    val officerPosition: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("industry")
    val industry: String,
    @SerializedName("location_name")
    val locationName: String
)