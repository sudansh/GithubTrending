package com.sudansh.trending.data.repository

import androidx.lifecycle.LiveData
import com.sudansh.trending.data.Resource
import com.sudansh.trending.data.db.dao.RepoDao
import com.sudansh.trending.data.db.entity.Repo
import com.sudansh.trending.data.network.ApiResponse
import com.sudansh.trending.data.network.ApiService
import com.sudansh.trending.data.network.AppExecutors
import com.sudansh.trending.data.network.NetworkBoundResource



class RepoRepository(
	val appExecutors: AppExecutors,
	val repoDao: RepoDao,
	val apiService: ApiService
) {

	fun getTrending(isFetch: Boolean = false): LiveData<Resource<List<Repo>>> {
		return object :
				NetworkBoundResource<List<Repo>, List<Repo>>(appExecutors) {
			override fun saveCallResult(item: List<Repo>) {
				repoDao.insert(item)
			}

			override fun shouldFetch(data: List<Repo>?): Boolean =
				isFetch || data == null || data.isEmpty()

			override fun loadFromDb(): LiveData<List<Repo>> =
				repoDao.getTrending()

			override fun createCall(): LiveData<ApiResponse<List<Repo>>> =
				apiService.getTrending()
		}.asLiveData()
	}
}