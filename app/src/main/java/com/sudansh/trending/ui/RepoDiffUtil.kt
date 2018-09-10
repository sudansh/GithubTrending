package com.sudansh.trending.ui

import android.support.v7.util.DiffUtil
import com.sudansh.trending.data.db.entity.Repo

class RepoDiffUtil(private val newList: List<Repo>, private val oldList: List<Repo>) :
		DiffUtil.Callback() {
	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
		newList[newItemPosition].name == oldList[oldItemPosition].name

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
		newList[newItemPosition] == oldList[oldItemPosition]

	override fun getOldListSize() = oldList.size

	override fun getNewListSize() = newList.size
}