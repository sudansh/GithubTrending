package com.sudansh.trending

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.sudansh.trending.data.Resource
import com.sudansh.trending.data.repository.RepoRepository
import com.sudansh.trending.data.db.dao.RepoDao
import com.sudansh.trending.data.db.entity.Repo
import com.sudansh.trending.data.network.ApiService
import com.sudansh.trending.util.InstantAppExecutors
import com.sudansh.trending.util.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class RepoRepositoryrTest {
	private val dao = mock(RepoDao::class.java)
	private val api = mock(ApiService::class.java)
	private val repo = RepoRepository(InstantAppExecutors(), dao, api)

	@Rule
	@JvmField
	val instantExecutorRule = InstantTaskExecutorRule()

	@Test
	fun loadUser() {
		repo.getTrending()
		verify(dao).getTrending()
	}

	@Test
	fun testNetworkCall() {
		val dbData = MutableLiveData<List<Repo>>()
		`when`(dao.getTrending()).thenReturn(dbData)
		val call = ApiUtil.successCall(createListRepo("foo", "bar", "zoo",10))
		`when`(api.getTrending()).thenReturn(call)
		val observer = mock<Observer<Resource<List<Repo>>>>()

		//fetch data from db
		repo.getTrending(false).observeForever(observer)

		//verify no apiService is called
		verify(api, never()).getTrending()

		val updatedDbData = MutableLiveData<List<Repo>>()
		`when`(dao.getTrending()).thenReturn(updatedDbData)
		dbData.value = null

		//force fetch from apiService
		repo.getTrending(true).observeForever(observer)

		//verify apiService is called
		verify(api, times(1)).getTrending()
	}
}