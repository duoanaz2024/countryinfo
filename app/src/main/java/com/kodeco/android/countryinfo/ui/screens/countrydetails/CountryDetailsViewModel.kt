package com.kodeco.android.countryinfo.ui.screens.countrydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.ui.screens.countryinfo.CountryInfoViewModel
import kotlinx.coroutines.launch

class CountryDetailsViewModel (private val countryId: Int,
                               private val repository: CountryRepository) : ViewModel() {

    class CountryDetailsViewModelFactory(private val countryId: Int,
                                         private val repository: CountryRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryDetailsViewModel(countryId, repository) as T
    }
    var country: Country? = null
    init {
        country = repository.getCountry(countryId)
    }

}