package com.kodeco.android.countryinfo.networking

import com.kodeco.android.countryinfo.networking.adapters.CountryAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun buildClient(): OkHttpClient =
    OkHttpClient.Builder().build()

fun buildRetrofit(): Retrofit {

    val moshi = Moshi.Builder()
        .add(CountryAdapter())
        .build()

    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

fun buildApiService(): RemoteApiService =
    buildRetrofit().create(RemoteApiService::class.java)
