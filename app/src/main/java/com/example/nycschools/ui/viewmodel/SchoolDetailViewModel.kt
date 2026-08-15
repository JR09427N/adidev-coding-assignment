package com.example.nycschools.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.nycschools.data.local.SatScoreEntity
import com.example.nycschools.data.local.SchoolEntity
import com.example.nycschools.data.repository.SchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: SchoolsRepository
) : ViewModel() {

    private val dbn: String = checkNotNull(savedStateHandle["dbn"])

    val school: Flow<SchoolEntity?> = repository.observeSchoolByDbn(dbn)

    val satScore: Flow<SatScoreEntity?> = repository.observeSatScoreByDbn(dbn)
}