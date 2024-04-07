package com.kodeco.android.countryinfo

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import com.kodeco.android.countryinfo.nav.CountryInfoNavHost
import com.kodeco.android.countryinfo.networking.NetworkStatusChecker
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(getSystemService(ConnectivityManager::class.java))
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {

                if (networkStatusChecker.hasInternetConnection()){
                    CountryInfoNavHost()

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
