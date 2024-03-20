package com.kodeco.android.countryinfo

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kodeco.android.countryinfo.networking.NetworkStatusChecker
import com.kodeco.android.countryinfo.networking.buildApiService
import com.kodeco.android.countryinfo.repositories.CountryRepositoryImpl
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoViewModel
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(getSystemService(ConnectivityManager::class.java))
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService by lazy { buildApiService() }

        setContent {
            MyApplicationTheme {

                if (networkStatusChecker.hasInternetConnection()){
                    CountryInfoScreen(
                        viewModel = viewModel(
                            factory = CountryInfoViewModel.CountryInfoViewModelFactory(
                                repository = CountryRepositoryImpl(apiService),
                            ),
                        ),
                    )


                }
                else{
                    CountryErrorScreen(
                        headline = "No Internet Connection",
                        subtitle = "Please Connect To Network"
                    ){
                        val intent = Intent(this, MainActivity::class.java)
                        finish()
                        startActivity(intent);
                    }
                    }

                }
            }
        }
}
