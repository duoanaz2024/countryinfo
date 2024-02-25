package com.kodeco.android.countryinfo.ui.components

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.networking.RemoteApiService
import com.kodeco.android.countryinfo.util.CountryInfoState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CountryInfoScreen(apiService: RemoteApiService) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        var countryList by rememberSaveable(Unit) {
            mutableStateOf(listOf<Country>())
        }
        var errorState by rememberSaveable { mutableIntStateOf(0) }


        val scope = rememberCoroutineScope()
        LaunchedEffect(key1 = true){

            scope.launch(Dispatchers.IO) {
                delay(1000)
                var countryState: CountryInfoState? = null
                try{
                    val response = apiService.getCountries()
                    if (response.isNotEmpty()){
                        countryState = CountryInfoState.Success(response)
                    }
                }catch (e: HttpException){
                    countryState = CountryInfoState.Error(e.message)
                }catch (e: IOException){
                    countryState = CountryInfoState.Error(e.message)
                }
                catch (e: Exception){
                    countryState = CountryInfoState.Loading
                }

                when (countryState) {
                    is CountryInfoState.Success -> {
                        countryList = countryState.countries
                        errorState = 0
                    }
                    is CountryInfoState.Error -> {
                        Log.d("INFO", "Exception Occurred: " + countryState.message)
                        countryList = emptyList()
                        errorState = 1

                    }
                    is CountryInfoState.Loading -> {
                        errorState = 0
                    }
                    else -> {}
                }


            }

        }

        if (countryList.isEmpty() && errorState == 0){
            Loading()
        }
        else if (countryList.isEmpty() && errorState == 1){
            CountryErrorScreen(
                headline = "Error",
                subtitle = "Something Went Wrong",
                onClick = {  })
        }
        else{
            CountryInfoList(countryList)
        }

    }

}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoScreenPreview() { }
