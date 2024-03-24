package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.R
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.ui.screens.countrydetails.CountryDetailsScreen


@Composable
fun CountryInfoList(
    countryList: List<Country>,
    onCountryRowTap: (Any?) -> Unit,
    aboutTap: () -> Unit,
    onRefreshClick: () -> Unit
) {


    Column {

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            Text(text = "Country",
                Modifier.padding(6.dp))

            Spacer(modifier = Modifier.weight((0.8f)))

            Button(
                onClick = onRefreshClick) {
                Text("Refresh")
            }

            Spacer(modifier = Modifier.weight((1f)))

            Image(
                painter = painterResource(id = R.drawable.icons8_info_94),
                contentDescription = "About",
                Modifier.padding(6.dp).clickable { aboutTap() },

            )

        }

        LazyColumn {
            items(countryList.size) { index ->
                CountryInfoRow(countryList[index]) {
                    onCountryRowTap(index)
                }
            }
        }

    }




}
