package com.example.nycschools.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nycschools.data.local.SatScoreEntity
import com.example.nycschools.data.local.SchoolEntity
import com.example.nycschools.ui.viewmodel.SchoolDetailViewModel

@Composable
fun SchoolDetailScreen(
    viewModel: SchoolDetailViewModel,
    contentPadding: PaddingValues = PaddingValues()
) {
    val school by viewModel.school.collectAsStateWithLifecycle(initialValue = null)
    val satScore by viewModel.satScore.collectAsStateWithLifecycle(initialValue = null)

    if (school == null) {
        Text(
            text = "School details are unavailable.",
            modifier = Modifier.padding(24.dp)
        )
        return
    }

    SchoolDetailContent(
        school = school!!,
        satScore = satScore,
        contentPadding = contentPadding
    )
}

@Composable
private fun SchoolDetailContent(
    school: SchoolEntity,
    satScore: SatScoreEntity?,
    contentPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = school.schoolName,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = school.borough ?: "Borough unavailable",
            style = MaterialTheme.typography.titleMedium
        )

        DetailSection(
            title = "About this school",
            value = school.overview ?: "No overview is available."
        )

        DetailSection(
            title = "Location",
            value = school.location ?: "Location unavailable"
        )

        DetailSection(
            title = "Phone",
            value = school.phoneNumber ?: "Phone number unavailable"
        )

        DetailSection(
            title = "Email",
            value = school.email ?: "Email unavailable"
        )

        DetailSection(
            title = "Website",
            value = school.website ?: "Website unavailable"
        )

        HorizontalDivider()

        Text(
            text = "SAT Scores",
            style = MaterialTheme.typography.titleLarge
        )

        if (satScore == null) {
            Text(
                text = "SAT scores are not available for this school."
            )
        } else {
            Text(
                text = "Based on ${satScore.numberOfTestTakers ?: "an unavailable number of"} test takers.",
                style = MaterialTheme.typography.bodyMedium
            )

            SatScoreCard(
                label = "Math",
                score = satScore.mathAverageScore
            )

            SatScoreCard(
                label = "Reading",
                score = satScore.readingAverageScore
            )

            SatScoreCard(
                label = "Writing",
                score = satScore.writingAverageScore
            )
        }
    }
}

@Composable
private fun DetailSection(
    title: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun SatScoreCard(
    label: String,
    score: String?
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = score ?: "Not available",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}