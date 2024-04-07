package com.kodeco.android.countryinfo.networking.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDto(
    val name: CountryNameDto,
    val capital: List<String>?,
    val population: Long,
    val area: Float,
    val flags: CountryFlagsDto
) {

    var commonName = name.common
    val mainCapital = capital?.firstOrNull() ?: "N/A"
    val flagUrl = flags.png
}
