package com.example.nycschools.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Query(
        """
            SELECT * FROM schools
            ORDER BY schoolName ASC
        """
    )
    fun observeSchools(): Flow<List<SchoolEntity>>

    @Query(
        """
            SELECT * FROM schools
            WHERE dbn = :dbn
            LIMIT 1
        """
    )
    fun observeSchoolByDbn(dbn: String): Flow<SchoolEntity?>

    @Upsert
    suspend fun upsertSchools(schools: List<SchoolEntity>)

    @Query("DELETE FROM schools")
    suspend fun clearSchools()
}