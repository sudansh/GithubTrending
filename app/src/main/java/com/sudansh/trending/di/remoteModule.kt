package com.sudansh.trending.di

import com.sudansh.trending.data.network.LiveDataCallAdapterFactory
import com.sudansh.trending.data.network.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = applicationContext {
	bean { createOkHttpClient() }
	bean { createWebService<ApiService>(get()) }
}

fun createOkHttpClient(): OkHttpClient {
	return OkHttpClient.Builder().apply {
		connectTimeout(60L, TimeUnit.SECONDS)
		readTimeout(60L, TimeUnit.SECONDS)
	}.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
	val retrofit = Retrofit.Builder()
		.baseUrl("http://localhost:8080/")
		.client(okHttpClient)
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(LiveDataCallAdapterFactory())
		.build()
	return retrofit.create(T::class.java)
}