package com.kodeco.android.countryinfo.ui.components

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kodeco.android.countryinfo.networking.RemoteApiService
import com.kodeco.android.countryinfo.util.CountryInfoState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException


private fun getCountryInfoFlow(apiService: RemoteApiService): Flow<CountryInfoState> {


    return flow {

        val response = apiService.getCountries()
        if (response.isNotEmpty()){
            emit(CountryInfoState.Success(response))
        }

    }

}



@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CountryInfoScreen(apiService: RemoteApiService) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        var countryState by remember {
            mutableStateOf<CountryInfoState>(CountryInfoState.Loading)
        }

        val scope = rememberCoroutineScope()

        when (countryState) {
            is CountryInfoState.Success -> {
                val countryList = (countryState as CountryInfoState.Success).countries
                CountryInfoList(countryList){
                    countryState = CountryInfoState.Loading
                }
            }
            is CountryInfoState.Error -> {
                CountryErrorScreen(
                    headline = "Error",
                    subtitle = "Something Went Wrong"
                ){
                    countryState = CountryInfoState.Loading
                }

            }
            is CountryInfoState.Loading -> {
                Loading()
                LaunchedEffect(key1 = true){
                    scope.launch(Dispatchers.IO) {
                        getCountryInfoFlow(apiService)
                            .catch { e ->
                                when (e) {
                                    is HttpException, is IOException -> {
                                        Log.d("INFO", "HTTP/IO Exception Occurred")
                                        emit(CountryInfoState.Error(e.message))
                                    }

                                    is IllegalStateException -> {
                                        Log.d("INFO", "Illegal State Exception Occurred")
                                        emit(CountryInfoState.Loading)
                                    }

                                    else -> {
                                        Log.d("INFO", "Exception Occurred")
                                        emit(CountryInfoState.Error(e.message))
                                    }
                                }

                            }
                            .collect {
                                value -> countryState = value
                            }

                    }

                }

            }
        }

    }

}

