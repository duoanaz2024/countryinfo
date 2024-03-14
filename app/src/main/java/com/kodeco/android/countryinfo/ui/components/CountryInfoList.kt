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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.flow.Flows
import com.kodeco.android.countryinfo.model.Country

@Composable
fun CountryInfoList(countryList: List<Country>, onRefreshClick: () -> Unit) {

    var selectedCountry: Country? by rememberSaveable { mutableStateOf(null) }

    Column {
        // TODO: Implement the Row composable here that contains the
        //  the tap/back flow data and the refresh button.

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            val tapCount by Flows.tapFlow.collectAsState(0)
            val backCount by Flows.backFlow.collectAsState(0)

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
            CountryDetailsScreen(country) { selectedCountry = null }
        } ?: run {
            LazyColumn {
                items(countryList) { country ->
                    CountryInfoRow(country) {
                        selectedCountry = country
                        Flows.tap()
                    }
                }
            }
        }

    }




}
