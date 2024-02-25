package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.model.Country

// TODO fill out CountryInfoList
@Composable
fun CountryInfoList(countryList: List<Country>, navigateToProfile: (Country) -> Unit) {

    LazyColumn {
        items(countryList.size) { index ->
            CountryInfoRow(country = countryList[index],
                navigateToProfile)
        }

    }

}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoListPreview() { }
