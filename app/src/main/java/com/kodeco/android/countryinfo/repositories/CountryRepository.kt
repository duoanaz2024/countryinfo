package com.kodeco.android.countryinfo.repositories

import com.kodeco.android.countryinfo.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    fun fetchCountries(): Flow<List<Country>>

    fun getCountry(index: Int): Country?
}