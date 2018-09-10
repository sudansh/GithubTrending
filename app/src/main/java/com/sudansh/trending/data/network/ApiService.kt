package com.sudansh.trending.data.network

import android.arch.lifecycle.LiveData
import com.sudansh.trending.data.db.entity.Repo
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

	@GET()
	fun getTrending(@Url url: String = "https://github-trending-api.now.sh/repositories?since=weekly"): LiveData<ApiResponse<List<Repo>>>
}