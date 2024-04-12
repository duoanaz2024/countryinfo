package com.kodeco.android.countryinfo.networking

import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.networking.adapters.WrappedCountryList
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApiService {

    @GET("all")
    @WrappedCountryList
    suspend fun getCountries(): Response<List<Country>>

}