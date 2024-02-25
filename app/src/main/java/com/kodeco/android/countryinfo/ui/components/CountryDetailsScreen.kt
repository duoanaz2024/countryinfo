package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.model.Country

@Composable
fun CountryDetailsScreen(country: Country) {
    val c: String? = country.capital?.get(0)
    val capital: String = c ?: "Not Applicable"
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)

        ){
            Column() {
                Text(text = "Name: " + country.commonName,
                    Modifier.padding(6.dp))

            }

        }

    }


}

// TODO fill out the preview. NOTE this is above-and-beyond the required
////  section of the homework assignment.
@Preview
@Composable
fun CountryDetailsScreenPreview() { }
