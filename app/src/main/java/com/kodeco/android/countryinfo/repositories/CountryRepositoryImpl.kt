package com.kodeco.android.countryinfo.repositories

import android.util.Log
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.networking.RemoteApiService
import com.kodeco.android.countryinfo.repositories.DataManager.favorites
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object DataManager {

    var favorites = setOf<String>()

    val countries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())

}

class CountryRepositoryImpl(private val apiService: RemoteApiService) : CountryRepository {

    override val countries: StateFlow<List<Country>> = DataManager.countries.asStateFlow()

    override suspend fun fetchCountries() {
        val response = apiService.getCountries()

        DataManager.countries.value = emptyList()
        DataManager.countries.value = try {
            if (response.isNotEmpty()) {
                response
                    .toMutableList()
                    .map { country ->
                        country.copy(isFavorite = favorites.contains(country.commonName))
                    }
            } else {
                throw Throwable("Something Went Wrong")
            }
        } catch (e: Exception) {
            throw Throwable("Something Went Wrong")
        }

    }

    override fun getCountry(index: Int): Country? {
        return DataManager.countries.value.getOrNull(index)
    }

    override fun favorite(country: Country) {
        favorites = if (favorites.contains(country.commonName)) {
            favorites - country.commonName
        } else {
            favorites + country.commonName
        }
        val index = DataManager.countries.value.indexOf(country)
        val mutableCountries = DataManager.countries.value.toMutableList()
        mutableCountries[index] = mutableCountries[index].copy(isFavorite = favorites.contains(country.commonName))
        DataManager.countries.value = mutableCountries.toList()
        Log.d("INFO", favorites.toString())
    }
}