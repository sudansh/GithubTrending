package com.sudansh.trending.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sudansh.trending.R
import com.sudansh.trending.data.db.entity.Repo
import com.sudansh.trending.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

	private var repo: Repo? = null
	private lateinit var binding: ActivityDetailBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
		repo = intent.getParcelableExtra(KEY_REPO)
		initUI()
	}

	private fun initUI() {
		repo?.let {
			binding.repo = repo
		}
	}

	companion object {
		const val KEY_REPO = "KEY_REPO"

		fun start(context: Context, location: Repo) {
			Intent(context, DetailActivity::class.java).apply {
				putExtra(KEY_REPO, location)
			}.also { context.startActivity(it) }
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return if (item.itemId == android.R.id.home) {
			onBackPressed()
			true
		} else {
			super.onOptionsItemSelected(item)
		}
	}
}