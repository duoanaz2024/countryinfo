package com.kodeco.android.countryinfo.networking.dto

import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class CountryFlagsDto(val png: String, val svg: String)
