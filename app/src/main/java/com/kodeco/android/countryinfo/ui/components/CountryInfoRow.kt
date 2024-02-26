package com.kodeco.android.countryinfo.ui.components

import android.content.Intent
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.model.Country
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.kodeco.android.countryinfo.CountryActivity
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme

@Composable
fun CountryInfoRow(country  : Country, navigateToDetails: (Country) -> Unit) {
    val c: String? = country.capital?.get(0)
    val capital: String = c ?: "Not Applicable"
    //val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        modifier = Modifier
            .clickable {
                //context.startActivity(CountryActivity.newIntent(context, country))
                navigateToDetails(country)
            }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)

    ){
        Column() {
            Text(text = "Name: " + country.commonName,
                Modifier.padding(6.dp))

            Text(text = "Capital: $capital",
                Modifier.padding(6.dp))
        }

    }

}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoRowPreview() { }
