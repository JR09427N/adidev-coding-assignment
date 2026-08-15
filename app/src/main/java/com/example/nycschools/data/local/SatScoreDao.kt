package com.example.nycschools.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SatScoreDao {
    @Query(
        """
        SELECT * FROM sat_scores
        WHERE dbn = :dbn
        LIMIT 1
        """
    )
    fun observeSatScoreByDbn(dbn: String): Flow<SatScoreEntity?>

    @Upsert
    suspend fun upsertSatScores(scores: List<SatScoreEntity>)

    @Query("DELETE FROM sat_scores")
    suspend fun clearSatScores()
}