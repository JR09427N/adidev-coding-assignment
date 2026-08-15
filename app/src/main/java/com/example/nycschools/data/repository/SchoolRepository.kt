package com.example.nycschools.data.repository

import com.example.nycschools.data.local.SatScoreDao
import com.example.nycschools.data.local.SatScoreEntity
import com.example.nycschools.data.local.SchoolDao
import com.example.nycschools.data.local.SchoolEntity
import com.example.nycschools.data.remote.NYCSchoolsApi
import com.example.nycschools.data.remote.SatScoreDto
import com.example.nycschools.data.remote.SchoolDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolRepository @Inject constructor(
    private val api: NYCSchoolsApi,
    private val schoolDao: SchoolDao,
    private val satScoreDao: SatScoreDao
) {

    fun observeSchools(): Flow<List<SchoolEntity>> {
        return schoolDao.observeSchools()
    }

    fun observeSchoolByDbn(dbn: String): Flow<SchoolEntity?> {
        return schoolDao.observeSchoolByDbn(dbn)
    }

    fun observeSatScoreByDbn(dbn: String): Flow<SatScoreEntity?> {
        return satScoreDao.observeSatScoreByDbn(dbn)
    }

    suspend fun refreshSchools() {
        val schools = api.getSchools()
        schoolDao.clearSchools()
        schoolDao.upsertSchools(schools.map { it.toEntity() })
    }

    suspend fun refreshSatScores() {
        val scores = api.getSatScores()
        satScoreDao.clearSatScores()
        satScoreDao.upsertSatScores(scores.map { it.toEntity() })
    }

    private fun SchoolDto.toEntity(): SchoolEntity {
        return SchoolEntity(
            dbn = dbn,
            schoolName = schoolName,
            borough = boro,
            overview = overview,
            phoneNumber = phoneNumber,
            email = email,
            website = website,
            location = location
        )
    }

    private fun SatScoreDto.toEntity(): SatScoreEntity {
        return SatScoreEntity(
            dbn = dbn,
            schoolName = schoolName,
            numberOfTestTakers = numberOfTestTakers,
            readingAverageScore = readingAverageScore,
            mathAverageScore = mathAverageScore,
            writingAverageScore = writingAverageScore
        )
    }
}