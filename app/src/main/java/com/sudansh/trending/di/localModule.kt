package com.sudansh.trending.di

import androidx.room.Room
import com.sudansh.trending.data.db.AppDatabase
import com.sudansh.trending.data.network.AppExecutors
import com.sudansh.trending.data.repository.RepoRepository
import org.koin.dsl.module

val localModule = module {
	single { AppExecutors() }
	single { RepoRepository(get(), get(), get()) }
	single {
		Room.databaseBuilder(get(), AppDatabase::class.java, "github-db").build()
	}
	single { get<AppDatabase>().repoDao() }
}