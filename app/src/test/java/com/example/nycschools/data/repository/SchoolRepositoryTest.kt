package com.example.nycschools.data.repository

import com.example.nycschools.data.local.SatScoreDao
import com.example.nycschools.data.local.SchoolDao
import com.example.nycschools.data.remote.NYCSchoolsApi
import com.example.nycschools.data.remote.SchoolDto
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SchoolsRepositoryTest {

    private val api: NYCSchoolsApi = mock()
    private val schoolDao: SchoolDao = mock()
    private val satScoreDao: SatScoreDao = mock()

    private val repository = SchoolsRepository(
        api = api,
        schoolDao = schoolDao,
        satScoreDao = satScoreDao
    )

    @Test
    fun refreshSchools_clearsCacheAndStoresMappedApiSchools() = runTest {
        val apiSchools = listOf(
            SchoolDto(
                dbn = "01M448",
                schoolName = "University Neighborhood High School",
                boro = "MANHATTAN",
                overview = "Test overview",
                phoneNumber = "212-555-0100",
                email = "school@example.com",
                website = "https://example.com",
                location = "200 Monroe Street"
            )
        )

        whenever(api.getSchools()).thenReturn(apiSchools)

        repository.refreshSchools()

        verify(api).getSchools()
        verify(schoolDao).clearSchools()
        verify(schoolDao).upsertSchools(
            argThat { schools ->
                schools.size == 1 &&
                        schools.first().dbn == "01M448" &&
                        schools.first().schoolName == "University Neighborhood High School"
            }
        )
    }
}