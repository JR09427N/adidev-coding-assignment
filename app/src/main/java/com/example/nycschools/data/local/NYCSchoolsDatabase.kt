package com.example.nycschools.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        SchoolEntity::class,
        SatScoreEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NYCSchoolsDatabase : RoomDatabase() {

    abstract fun schoolDao(): SchoolDao

    abstract fun satScoreDao(): SatScoreDao
}