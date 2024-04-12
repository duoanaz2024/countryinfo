package com.kodeco.android.countryinfo.nav

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.android.countryinfo.networking.preferences.CountryPrefsImpl
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.ui.screens.about.CountryAppAboutScreen
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsViewModel
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoViewModel
import com.kodeco.android.countryinfo.ui.screens.settings.CountryAppSettingsScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CountryInfoNavHost(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "countryList"){

        composable("countryList"){
            CountryInfoScreen(
                viewModel = hiltViewModel(),
                onCountryRowTap = { countryIndex ->
                    navController.navigate("details/$countryIndex")
                },
                onSettingsTap = {
                    navController.navigate("settingsScreen")
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
                countryId = countryIndex,
                viewModel = hiltViewModel()
            ) {
                navController.navigateUp()
            }

        }

        composable("aboutScreen"){
            CountryAppAboutScreen(
            ){
                navController.navigateUp()
            }
        }

        composable("settingsScreen"){
            CountryAppSettingsScreen(
                viewModel = hiltViewModel()
            ){
                navController.navigateUp()
            }
        }

    }

}