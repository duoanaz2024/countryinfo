package com.kodeco.android.countryinfo

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.countryinfo.networking.NetworkStatusChecker
import com.kodeco.android.countryinfo.networking.buildApiService
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.components.CountryInfoScreen
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
                    CountryInfoScreen(apiService){
                        startActivity(CountryActivity.newIntent(this, it))
                    }

                }
                else{
                    CountryErrorScreen(
                        headline = "No Internet Connection",
                        subtitle = "Please Connect To Network")
                }
                }
            }
        }
}
