package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsScreen


@Composable
fun CountryInfoList(countryList: List<Country>, onRefreshClick: () -> Unit) {

    var selectedCountry: Country? by rememberSaveable { mutableStateOf(null) }
    var tapCount by rememberSaveable { mutableIntStateOf(0) }
    var backCount by rememberSaveable { mutableIntStateOf(0) }

    Column {

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            Text(text = "Taps: $tapCount",
                Modifier.padding(6.dp))

            Spacer(modifier = Modifier.weight((1f)))

            Button(
                onClick = onRefreshClick) {
                Text("Refresh")
            }

            Spacer(modifier = Modifier.weight((1f)))
            Text(text = "Back: $backCount",
                Modifier.padding(6.dp))

        }

        selectedCountry?.let { country ->
            CountryDetailsScreen(country) {
                selectedCountry = null
                backCount += 1
            }
        } ?: run {
            LazyColumn {
                items(countryList) { country ->
                    CountryInfoRow(country) {
                        selectedCountry = country
                        tapCount += 1
                    }
                }
            }
        }

    }




}
