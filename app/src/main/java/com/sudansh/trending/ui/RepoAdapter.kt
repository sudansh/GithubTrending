package com.sudansh.trending.ui

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.sudansh.trending.R
import com.sudansh.trending.databinding.ItemRepoBinding
import com.sudansh.trending.data.db.entity.Repo

class RepoAdapter(private var callback: OnItemClickListener) :
	androidx.recyclerview.widget.RecyclerView.Adapter<RepoViewHolder>() {
	private val listRepo: MutableList<Repo> = mutableListOf()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
		val binding = DataBindingUtil.inflate<ItemRepoBinding>(
			LayoutInflater.from(parent.context),
			R.layout.item_repo,
			parent,
			false
		)
		return RepoViewHolder(binding)
	}

	override fun getItemCount() = listRepo.size

	override fun onBindViewHolder(vh: RepoViewHolder, position: Int) {
		val binding = vh.binding()
		binding.repo = listRepo[position]
		binding.mainContainer.setOnClickListener {
			callback.onItemClick(listRepo[position], binding.name, binding.desc)
		}
		binding.executePendingBindings()
	}

	fun updateItems(data: List<Repo>) {
		val diffResult = DiffUtil.calculateDiff(RepoDiffUtil(data, listRepo))
		listRepo.clear()
		listRepo.addAll(data)
		diffResult.dispatchUpdatesTo(this)
	}
}

class RepoViewHolder(private val binding: ItemRepoBinding) :
	androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

	fun binding() = binding
}

interface OnItemClickListener {
	fun onItemClick(repo: Repo, name: TextView, description: TextView)
}
