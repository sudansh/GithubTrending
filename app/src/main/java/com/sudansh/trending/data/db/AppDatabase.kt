package com.sudansh.trending.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sudansh.trending.data.db.dao.RepoDao
import com.sudansh.trending.data.db.entity.Repo

@Database(entities = [Repo::class],
		  exportSchema = false,
		  version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun repoDao(): RepoDao
}