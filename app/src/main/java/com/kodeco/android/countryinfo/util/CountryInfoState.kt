package com.kodeco.android.countryinfo.util

import android.os.Parcelable
import com.kodeco.android.countryinfo.model.Country
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CountryInfoState : Parcelable {
    class Success(val countries: List<Country>):CountryInfoState(){
    }
    class Error(val message: String?) : CountryInfoState(){
    }
    class CountryDetails(val country: Country, val countries: List<Country>):CountryInfoState(){
    }

    data object Loading : CountryInfoState()
}