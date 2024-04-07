package com.kodeco.android.countryinfo.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Country(
    val name: CountryName,
    val capital: List<String>?,
    val population: Long,
    val area: Double,
    val isFavorite: Boolean = false,
    val flags: CountryFlags
) : Parcelable {

    @IgnoredOnParcel
    var commonName = name.common

    @IgnoredOnParcel
    val commonFlag = flags.png
}
