package com.example.nycschools.domain

data class SatScore(
    val dbn: String,
    val schoolName: String,
    val numberOfTestTakers: String?,
    val readingAverageScore: String?,
    val mathAverageScore: String?,
    val writingAverageScore: String?
)
