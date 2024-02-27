package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.util.CountryInfoState

// TODO fill out CountryInfoList
@Composable
fun CountryInfoList(countryList: List<Country>, updateDetails: (CountryInfoState) -> Unit) {

    LazyColumn {
        items(countryList.size) { index ->
            CountryInfoRow(
                country = countryList[index], countryList, updateDetails
                )
        }

    }

}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoListPreview() { }
