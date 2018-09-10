package com.sudansh.trending

import android.app.Application
import com.sudansh.trending.di.appModule
import com.sudansh.trending.di.localModule
import com.sudansh.trending.di.remoteModule
import org.koin.android.ext.android.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin(listOf(appModule, remoteModule, localModule))
	}

}