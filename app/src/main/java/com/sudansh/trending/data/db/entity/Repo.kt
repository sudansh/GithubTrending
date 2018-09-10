package com.sudansh.trending.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "repo")
@Parcelize
data class Repo(
	@PrimaryKey val name: String,
	val description: String,
	val author: String,
	val stars: Int,
	val forks: Int,
	val language: String
) : Parcelable {
	fun getLanguageString() = if (language.isBlank()) "Unknown"
	else language
}