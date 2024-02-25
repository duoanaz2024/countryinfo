package com.kodeco.android.countryinfo.networking

import com.kodeco.android.countryinfo.model.Country
import retrofit2.http.GET

interface RemoteApiService {

    @GET("all")
    suspend fun getCountries(): List<Country>

}