package com.example.nycschools.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.data.local.SchoolEntity
import com.example.nycschools.data.repository.SchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsListViewModel @Inject constructor(
    private val repository: SchoolsRepository
) : ViewModel() {

    val schools: StateFlow<List<SchoolEntity>> =
        repository.observeSchools()
            .catch {
                emit(emptyList())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _errorMessage.value = null

            try {
                repository.refreshSchools()
                repository.refreshSatScores()
            } catch (exception: Exception) {
                _errorMessage.value =
                    "Unable to refresh school data. Check your internet connection and try again."
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}