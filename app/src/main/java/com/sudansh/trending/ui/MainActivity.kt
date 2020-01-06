package com.sudansh.trending.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.sudansh.trending.R
import com.sudansh.trending.data.Resource
import com.sudansh.trending.data.Status
import com.sudansh.trending.data.db.entity.Repo
import com.sudansh.trending.databinding.ActivityMainBinding
import com.sudansh.trending.util.action
import com.sudansh.trending.util.observeNonNull
import com.sudansh.trending.util.snack
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private val viewModel by viewModel<MainViewModel>()
    private val repoAdapter by lazy { RepoAdapter(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val divider =
            androidx.recyclerview.widget.DividerItemDecoration(
                recyclerView.context,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            ).apply {
                setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.item_divider)!!)
            }

        with(recyclerView) {
            adapter = repoAdapter
            addItemDecoration(divider)
        }
        swipe.setOnRefreshListener(viewModel::refresh)

        viewModel.trending.observeNonNull(this) {
            updateUI(it)
        }
    }

    private fun updateUI(resource: Resource<List<Repo>>) {
        when (resource.status) {
            Status.SUCCESS -> {
                if (swipe.isRefreshing) swipe.isRefreshing = false
                repoAdapter.updateItems(resource.data.orEmpty())
                viewModel.isLoading.set(false)
            }
            Status.LOADING -> viewModel.isLoading.set(true)
            Status.ERROR -> {
                if (swipe.isRefreshing) swipe.isRefreshing = false
                viewModel.isLoading.set(false)
                showError()
            }
        }

    }

    private fun showError() {
        mainContainer.snack(getString(R.string.error_message), Snackbar.LENGTH_INDEFINITE) {
            action(getString(R.string.yes)) { viewModel.refresh() }
        }
    }

    override fun onItemClick(repo: Repo, name: TextView, description: TextView) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            Pair.create<View, String>(
                name,
                getString(
                    R.string.transitionName
                )
            ),
            Pair.create<View, String>(
                description,
                getString(R.string.transitionDescription)
            )
        )
        Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.KEY_REPO, repo)
        }.also { startActivity(it, options.toBundle()) }

    }
}