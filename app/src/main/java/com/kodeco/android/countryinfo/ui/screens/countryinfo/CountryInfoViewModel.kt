package com.kodeco.android.countryinfo.ui.screens.countryinfo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.model.Country
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.util.CountryInfoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class CountryInfoViewModel(private val repository: CountryRepository) : ViewModel() {

    class CountryInfoViewModelFactory(private val repository: CountryRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryInfoViewModel(repository) as T
    }

    private val _uiState = MutableStateFlow<CountryInfoState>(CountryInfoState.Loading)
    val uiState: StateFlow<CountryInfoState> = _uiState

    init {
        refreshCountries()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun refreshCountries(){
        _uiState.value = CountryInfoState.Loading
        viewModelScope.launch {
            try{
                delay(1_500)
                repository.fetchCountries()
            } catch (e: Exception) {
                Log.d("INFO", "Exception Occurred")
                _uiState.value = CountryInfoState.Error(e.message)
            }
            repository.countries
                .catch { e ->
                    Log.d("INFO", "Exception Occurred")
                    _uiState.value = CountryInfoState.Error(e.message)

                }
                .collect {
                        value -> _uiState.value = CountryInfoState.Success(value)
                }

        }


    }

    fun favorite(country: Country) {
        viewModelScope.launch {
            repository.favorite(country)
        }
    }


}