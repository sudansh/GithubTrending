package com.sudansh.trending.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sudansh.trending.data.db.dao.RepoDao
import com.sudansh.trending.data.db.entity.Repo

@Database(entities = [Repo::class],
		  exportSchema = false,
		  version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun repoDao(): RepoDao
}