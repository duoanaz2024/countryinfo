package com.kodeco.android.countryinfo.ui.screens.countryinfo

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.repositories.CountryRepository
import com.kodeco.android.countryinfo.util.CountryInfoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.IOException


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class CountryInfoViewModel(private val repository: CountryRepository) : ViewModel() {

    class CountryInfoViewModelFactory(private val repository: CountryRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CountryInfoViewModel(repository) as T
    }


    private val counterFlow: StateFlow<Int>
        get() = _counterFlow

    private val _counterFlow = MutableStateFlow(0)

    private val _uiState = MutableStateFlow<CountryInfoState>(CountryInfoState.Loading(counterFlow.value))
    val uiState: StateFlow<CountryInfoState> = _uiState

    init {
        refreshCountries()
        viewModelScope.launch {
            while (true){
                delay(1000)
                _counterFlow.value++
            }

        }

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun refreshCountries(){
        _uiState.value = CountryInfoState.Loading(counterFlow.value)
        viewModelScope.launch {

            repository.fetchCountries()
                .catch { e ->
                    when (e) {
                        is HttpException, is IOException -> {
                            Log.d("INFO", "HTTP/IO Exception Occurred")
                            _uiState.value = CountryInfoState.Error(e.message)
                        }

                        is IllegalStateException -> {
                            Log.d("INFO", "Illegal State Exception Occurred")
                            _uiState.value = CountryInfoState.Loading(counterFlow.value)
                        }

                        else -> {
                            Log.d("INFO", "Exception Occurred")
                            _uiState.value = CountryInfoState.Error(e.message)
                        }
                    }

                }
                .collect {
                        value -> _uiState.value = CountryInfoState.Success(value)
                }

        }


    }


}