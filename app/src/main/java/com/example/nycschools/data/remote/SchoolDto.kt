package com.example.nycschools.data.remote

import com.google.gson.annotations.SerializedName

data class SchoolDto(
    @SerializedName("dbn")
    val dbn: String,

    @SerializedName("school_name")
    val schoolName: String,

    @SerializedName("boro")
    val boro: String?,

    @SerializedName("overview_paragraph")
    val overview: String?,

    @SerializedName("phone_number")
    val phoneNumber: String?,

    @SerializedName("school_email")
    val email: String?,

    @SerializedName("website")
    val website: String?,

    @SerializedName("location")
    val location: String?
)
