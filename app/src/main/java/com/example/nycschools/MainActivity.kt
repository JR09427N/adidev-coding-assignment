package com.example.nycschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nycschools.ui.AppNavigation
import com.example.nycschools.ui.theme.NYCSchoolsTheme
import com.example.nycschools.ui.viewmodel.SchoolsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NYCSchoolsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SchoolsApp()
                }
            }
        }
    }
}

@Composable
private fun SchoolsApp() {
    val viewModel: SchoolsListViewModel = viewModel()

    AppNavigation(viewModel = viewModel)
}