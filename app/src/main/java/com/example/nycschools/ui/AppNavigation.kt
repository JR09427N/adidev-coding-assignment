package com.example.nycschools.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import com.example.nycschools.ui.screens.SchoolDetailScreen
import com.example.nycschools.ui.screens.SchoolsListScreen
import com.example.nycschools.ui.viewmodel.SchoolDetailViewModel
import com.example.nycschools.ui.viewmodel.SchoolsListViewModel
import androidx.compose.material3.ExperimentalMaterial3Api

private const val SCHOOLS_LIST_ROUTE = "schools"
const val SCHOOL_DETAIL_ROUTE = "school_detail"
const val DBN_ARGUMENT = "dbn"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    viewModel: SchoolsListViewModel,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentRoute == SCHOOLS_LIST_ROUTE) {
                            "NYC High Schools"
                        } else {
                            "School Details"
                        }
                    )
                },
                navigationIcon = {
                    if (currentRoute != SCHOOLS_LIST_ROUTE) {
                        TextButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Text("Back")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SCHOOLS_LIST_ROUTE
        ) {
            composable(SCHOOLS_LIST_ROUTE) {
                SchoolsListScreen(
                    viewModel = viewModel,
                    contentPadding = innerPadding,
                    onSchoolClick = { dbn ->
                        navController.navigate(
                            "$SCHOOL_DETAIL_ROUTE/$dbn"
                        )
                    }
                )
            }

            composable("$SCHOOL_DETAIL_ROUTE/{$DBN_ARGUMENT}") {
                val detailViewModel: SchoolDetailViewModel = hiltViewModel()

                SchoolDetailScreen(
                    viewModel = detailViewModel,
                    contentPadding = innerPadding
                )
            }
        }
    }
}