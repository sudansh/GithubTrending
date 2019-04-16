package com.sudansh.trending.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "repo")
@Parcelize
data class Repo(
	@PrimaryKey val name: String,
	val description: String,
	val author: String,
	val stars: Int,
	val forks: Int,
	val language: String?
) : Parcelable {
	fun getLanguageString() = if (language.isNullOrBlank()) "Unknown"
	else language
}