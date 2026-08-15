package com.example.nycschools.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nycschools.data.local.SchoolEntity
import com.example.nycschools.ui.viewmodel.SchoolsListViewModel

@Composable
fun SchoolsListScreen(
    viewModel: SchoolsListViewModel,
    contentPadding: PaddingValues = PaddingValues(),
    onSchoolClick: (String) -> Unit
) {
    val schools by viewModel.schools.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    when {
        isRefreshing && schools.isEmpty() -> {
            LoadingContent()
        }

        errorMessage != null && schools.isEmpty() -> {
            MessageContent(
                message = errorMessage ?: "Something went wrong."
            )
        }

        schools.isEmpty() -> {
            MessageContent(
                message = "No NYC high schools found."
            )
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = schools,
                    key = { school -> school.dbn }
                ) { school ->
                    SchoolListItem(
                        school = school,
                        onClick = { onSchoolClick(school.dbn) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SchoolListItem(
    school: SchoolEntity,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = school.schoolName,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = school.borough ?: "Borough unavailable",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = school.location ?: "Location unavailable",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MessageContent(
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
    }
}