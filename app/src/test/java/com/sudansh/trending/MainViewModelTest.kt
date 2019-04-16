package com.sudansh.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sudansh.trending.data.Resource
import com.sudansh.trending.data.db.entity.Repo
import com.sudansh.trending.data.repository.RepoRepository
import com.sudansh.trending.ui.MainViewModel
import com.sudansh.trending.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    @Mock
    lateinit var repo: RepoRepository
    @Mock
    lateinit var results: Observer<Resource<List<Repo>>>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)

        viewModel = MainViewModel(repo)
        viewModel.trending.observeForever(results)
    }

    @Test
    fun testRefresh() {
        viewModel.trending.observeForever(mock())
        viewModel.refresh()
        //verify getTrending is called with true
        verify(repo).getTrending(true)
    }

    @Test
    fun testGetTrending() {
        viewModel.trending.observeForever(mock())
        //verify getTrending is called with false
        verify(repo).getTrending(false)
    }

}