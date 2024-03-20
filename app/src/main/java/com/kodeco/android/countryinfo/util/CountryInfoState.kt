package com.kodeco.android.countryinfo.util

import android.os.Parcelable
import com.kodeco.android.countryinfo.model.Country
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CountryInfoState : Parcelable {
    class Success(val countries: List<Country>):CountryInfoState(){
    }
    class Error(val message: String?) : CountryInfoState(){
    }

    data class Loading(val upTime: Int) : CountryInfoState()
}