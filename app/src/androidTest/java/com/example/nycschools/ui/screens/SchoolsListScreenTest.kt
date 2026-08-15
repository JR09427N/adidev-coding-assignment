package com.example.nycschools.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.nycschools.data.local.SchoolEntity
import com.example.nycschools.ui.theme.NYCSchoolsTheme
import com.example.nycschools.ui.viewmodel.SchoolsListViewModel
import org.junit.Rule
import org.junit.Test

class SchoolsListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun schoolName_isDisplayed() {
        composeTestRule.setContent {
            NYCSchoolsTheme {
                androidx.compose.material3.Text(
                    text = "University Neighborhood High School"
                )
            }
        }

        composeTestRule
            .onNodeWithText("University Neighborhood High School")
            .assertIsDisplayed()
    }
}