package com.example.nycschools.domain

data class School(
    val dbn: String,
    val schoolName: String,
    val borough: String?,
    val overview: String?,
    val phoneNumber: String?,
    val email: String?,
    val website: String?,
    val location: String?
)
