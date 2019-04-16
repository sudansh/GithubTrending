package com.sudansh.trending.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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