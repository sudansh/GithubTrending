package com.sudansh.trending.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sudansh.trending.data.Resource
import com.sudansh.trending.data.db.entity.Repo
import com.sudansh.trending.data.repository.RepoRepository
import com.sudansh.trending.util.switch


class MainViewModel(private val repo: RepoRepository) : ViewModel() {
	private val refresh: MutableLiveData<Boolean> = MutableLiveData()
	val isLoading = ObservableBoolean(true)
	val trending: LiveData<Resource<List<Repo>>> =
		refresh.switch { startLoad ->
			repo.getTrending(startLoad)
		}

	init {
		refresh.value = false
	}

	fun refresh() {
		refresh.value = true
	}
}