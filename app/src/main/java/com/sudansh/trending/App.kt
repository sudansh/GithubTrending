package com.sudansh.trending

import android.app.Application
import com.sudansh.trending.di.appModule
import com.sudansh.trending.di.localModule
import com.sudansh.trending.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			// declare used Android context
			androidContext(this@App)
			// declare modules
			modules(listOf(remoteModule, localModule, appModule))
		}
	}

}