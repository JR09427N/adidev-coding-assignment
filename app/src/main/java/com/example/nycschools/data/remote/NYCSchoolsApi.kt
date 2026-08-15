package com.example.nycschools.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NYCSchoolsApi {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(
        @Query("\$limit") limit: Int = 500
    ): List<SchoolDto>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSatScores(): List<SatScoreDto>
}