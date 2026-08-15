package com.example.nycschools.data.remote

import com.google.gson.annotations.SerializedName

data class SatScoreDto(
    @SerializedName("dbn")
    val dbn: String,

    @SerializedName("school_name")
    val schoolName: String,

    @SerializedName("num_of_sat_test_takers")
    val numberOfTestTakers: String?,

    @SerializedName("sat_critical_reading_avg_score")
    val readingAverageScore: String?,

    @SerializedName("sat_math_avg_score")
    val mathAverageScore: String?,

    @SerializedName("sat_writing_avg_score")
    val writingAverageScore: String?
)