package com.example.nycschools.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schools")
data class SchoolEntity(
    @PrimaryKey
    val dbn: String,

    val schoolName: String,
    val borough: String?,
    val overview: String?,
    val phoneNumber: String?,
    val email: String?,
    val website: String?,
    val location: String?
)
