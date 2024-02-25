package com.kodeco.android.countryinfo

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.ui.components.CountryDetailsScreen
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

class CountryActivity : AppCompatActivity() {


    private val country by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(COUNTRY_ID, Country::class.java)
        }
        else{
            intent?.getParcelableExtra(COUNTRY_ID)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                country?.let { CountryDetailsScreen(it) }
            }
        }
    }

    companion object {
        private const val COUNTRY_ID = "country_id"
        fun newIntent(context: Context, country: Country) =
            Intent(context, CountryActivity::class.java).apply {
                putExtra(COUNTRY_ID, country)
            }
    }
}