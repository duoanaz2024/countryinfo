package com.kodeco.android.countryinfo.networking.dto
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class CountryNameDto(val common: String)
