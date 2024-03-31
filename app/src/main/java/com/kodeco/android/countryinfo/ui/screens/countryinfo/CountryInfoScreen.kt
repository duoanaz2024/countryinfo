package com.kodeco.android.countryinfo.ui.screens.countryinfo

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.components.CountryInfoList
import com.kodeco.android.countryinfo.ui.components.Loading
import com.kodeco.android.countryinfo.util.CountryInfoState

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CountryInfoScreen(
    viewModel: CountryInfoViewModel,
    onCountryRowTap: (Any?) -> Unit,
    aboutTap: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        val countryState = viewModel.uiState.collectAsState()

        when (val curState = countryState.value) {
            is CountryInfoState.Success -> {
                val countryList = curState.countries
                CountryInfoList(
                    countryList=countryList,
                    onCountryRowTap=onCountryRowTap,
                    aboutTap=aboutTap,
                    onFavorite={country -> viewModel.favorite(country)}
                ){
                    viewModel.refreshCountries()
                }
            }
            is CountryInfoState.Error -> {
                CountryErrorScreen(
                    headline = "Error",
                    subtitle = "Something Went Wrong"
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

