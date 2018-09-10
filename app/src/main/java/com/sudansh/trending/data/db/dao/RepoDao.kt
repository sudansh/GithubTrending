package com.sudansh.trending.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sudansh.trending.data.db.entity.Repo

@Dao
interface RepoDao {

	@Query("SELECT * FROM repo")
	fun getTrending(): LiveData<List<Repo>>

	@Query("SELECT * FROM repo WHERE name =:name")
	fun findByName(name: String): LiveData<Repo>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(repo: Repo)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(list: List<Repo>)
}