package com.example.nycschools.di

import android.content.Context
import androidx.room.Room
import com.example.nycschools.data.local.NYCSchoolsDatabase
import com.example.nycschools.data.local.SatScoreDao
import com.example.nycschools.data.local.SchoolDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NYCSchoolsDatabase {
        return Room.databaseBuilder(
            context,
            NYCSchoolsDatabase::class.java,
            "nyc_schools_database"
        ).build()
    }

    @Provides
    fun provideSchoolDao(
        database: NYCSchoolsDatabase
    ): SchoolDao {
        return database.schoolDao()
    }

    @Provides
    fun provideSatScoreDao(
        database: NYCSchoolsDatabase
    ): SatScoreDao {
        return database.satScoreDao()
    }
}