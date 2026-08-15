package com.example.nycschools.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sat_scores")
data class SatScoreEntity(
    @PrimaryKey
    val dbn: String,

    val schoolName: String,
    val numberOfTestTakers: String?,
    val readingAverageScore: String?,
    val mathAverageScore: String?,
    val writingAverageScore: String?
)
