package com.sudansh.trending.di

import android.arch.persistence.room.Room
import com.sudansh.trending.data.network.AppExecutors
import com.sudansh.trending.data.repository.RepoRepository
import com.sudansh.trending.data.db.AppDatabase
import org.koin.dsl.module.applicationContext

val localModule = applicationContext {
	bean { AppExecutors() }
	bean { RepoRepository(get(), get(), get()) }
	bean {
		Room.databaseBuilder(get(), AppDatabase::class.java, "github-db").build()
	}
	bean { get<AppDatabase>().repoDao() }
}