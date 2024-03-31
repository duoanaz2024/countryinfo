package com.kodeco.android.countryinfo.repositories

import android.util.Log
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.networking.RemoteApiService
import com.kodeco.android.countryinfo.networking.buildApiService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

object DataManager {
    var countries = mutableListOf<Country>()
}

@OptIn(DelicateCoroutinesApi::class)
class CountryRepositoryImpl(private val apiService: RemoteApiService) : CountryRepository {


    /* Portrait Changes reinitialize the CountryRepositoryImpl, hence data is lost
    private var countries = mutableListOf<Country>()
    init{
        // Added to handle the landscape/portrait orientation changes as a workaround
        // Find out the best approach
        GlobalScope.launch {
            fetchCountries().collect()
        }
    }*/

    override fun fetchCountries(): Flow<List<Country>> {
        return flow {

            val response = apiService.getCountries()

            if (response.isNotEmpty()) {
                DataManager.countries.clear()
                DataManager.countries.addAll(response)
                emit(DataManager.countries)
            }
            else{
                throw Throwable("Something Went Wrong")
            }
        }
    }

    override fun getCountry(index: Int): Country? {
        return DataManager.countries.getOrNull(index)
    }
}