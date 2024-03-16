package com.kodeco.android.countryinfo.repositories

import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.networking.RemoteApiService
import com.kodeco.android.countryinfo.networking.buildApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl(private val apiService: RemoteApiService) : CountryRepository {

    private var countries: List<Country> = emptyList()
    override fun fetchCountries(): Flow<List<Country>> {
        return flow {

            val response = apiService.getCountries()
            if (response.isNotEmpty()) {
                countries = response
                emit(response)

            }
        }
    }

    override fun getCountry(index: Int): Country? {
        if (index < 0 )
            return null
        return countries[index]
    }
}