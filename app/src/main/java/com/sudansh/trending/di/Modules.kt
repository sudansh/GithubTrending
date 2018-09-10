package com.sudansh.trending.di

import com.sudansh.trending.ui.MainViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {

	viewModel { MainViewModel(get()) }
}