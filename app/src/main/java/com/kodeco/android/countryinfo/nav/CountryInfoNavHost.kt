package com.kodeco.android.countryinfo.nav

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.ui.screens.about.CountryAppAboutScreen
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsViewModel
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CountryInfoNavHost(repository: CountryRepository){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "countryList"){

        composable("countryList"){
            CountryInfoScreen(
                viewModel = viewModel(
                    factory = CountryInfoViewModel.CountryInfoViewModelFactory(
                        repository = repository,
                    ),
                ),{ countryIndex ->
                    navController.navigate("details/$countryIndex")
                }
            ){
                navController.navigate("aboutScreen")
            }
        }

        composable("details/{countryIndex}",
            arguments = listOf(navArgument("countryIndex") { type = NavType.IntType})){
                backStackEntry ->
            val countryIndex = backStackEntry.arguments?.getInt("countryIndex") ?: 0

            CountryDetailsScreen(
                viewModel = viewModel(
                    factory = CountryDetailsViewModel.CountryDetailsViewModelFactory(
                        countryId = countryIndex, repository = repository
                    ),
                )
            ) {
                navController.popBackStack()
            }

        }

        composable("aboutScreen"){
            CountryAppAboutScreen(
            ){
                navController.popBackStack()
            }
        }

    }

}