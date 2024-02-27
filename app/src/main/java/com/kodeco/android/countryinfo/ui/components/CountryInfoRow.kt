package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.model.Country
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.util.CountryInfoState

@Composable
fun CountryInfoRow(country: Country, countryList: List<Country>, updateDetails: (CountryInfoState) -> Unit) {
    val c: String? = country.capital?.get(0)
    val capital: String = c ?: "Not Applicable"
    //val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        modifier = Modifier
            .clickable {
                updateDetails(CountryInfoState.CountryDetails(country, countryList))
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
