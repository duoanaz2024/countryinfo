package com.kodeco.android.countryinfo.ui.screens.countrydetails

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.util.CountryInfoState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountryDetailsScreen(viewModel: CountryDetailsViewModel,
                         onNavigateUp: () -> Unit) {
    val country = viewModel.country
    if (country != null){
        val c: String? = country.capital?.get(0)
        val capital: String = c ?: "Not Applicable"
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Scaffold(
                containerColor = Color.White,
                contentColor = Color.Black,

                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = country.commonName)
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.White,
                            titleContentColor = Color.Black,
                            navigationIconContentColor = Color.Black,
                            actionIconContentColor = Color.Black
                        ),
                        navigationIcon = {
                            IconButton(onClick = {
                                onNavigateUp()
                            }) {
                                Icon(Icons.Filled.ArrowBack, "backIcon")
                            }
                        }
                    )
                }, content = {
                    Column() {
                        Spacer(modifier = Modifier.height(75.dp))
                        Text(text = "Capital: $capital",
                            Modifier.padding(6.dp))
                        Text(text = "Population: " + country.population,
                            Modifier.padding(6.dp))
                        Text(text = "Area: " + country.area,
                            Modifier.padding(6.dp))
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(country.commonFlag)
                                .crossfade(true)
                                .build(),
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentDescription = "Country Flag",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.padding(6.dp)
                        )


                    }

                })

        }

    }
    else{
        Log.d("INFO", "Country is Null")
    }


}
