package com.kodeco.android.countryinfo.ui.screens.countryinfo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.components.CountryInfoList
import com.kodeco.android.countryinfo.ui.components.Loading
import com.kodeco.android.countryinfo.util.CountryInfoState
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CountryInfoScreen(
    viewModel: CountryInfoViewModel,
    onCountryRowTap: (Any?) -> Unit,
    onSettingsTap: () -> Unit,
    aboutTap: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        val countryState = viewModel.uiState.collectAsState()
        val favoritesEnabled = viewModel.pref.getFavoritesFeatureEnabled().collectAsState(initial = false)

        when (val curState = countryState.value) {
            is CountryInfoState.Success -> {
                val countryList = curState.countries
                CountryInfoList(
                    favoritesEnabled=favoritesEnabled,
                    countryList=countryList,
                    onCountryRowTap=onCountryRowTap,
                    aboutTap=aboutTap,
                    onSettingsTap=onSettingsTap,
                    onFavorite={country -> viewModel.favorite(country)}
                ){
                    viewModel.refreshCountries()
                }
            }
            is CountryInfoState.Error -> {
                CountryErrorScreen(
                    headline = "Error",
                    subtitle = curState.message ?: "Something Went Wrong"
                ){
                    viewModel.refreshCountries()
                }

            }
            is CountryInfoState.Loading -> {
                Loading(aboutTap=aboutTap){
                    viewModel.refreshCountries()
                }

            }

        }

    }

}

