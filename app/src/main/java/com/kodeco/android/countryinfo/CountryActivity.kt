package com.kodeco.android.countryinfo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

class CountryActivity : AppCompatActivity() {

    private val country: Country by lazy {
        intent?.getParcelableExtra(COUNTRY_ID)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Text(text = "Hello World")
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