package com.sudansh.trending

import com.sudansh.trending.data.db.entity.Repo

fun createRepo(name: String,
	description: String,
	author: String,
	stars: Int): Repo {
	return Repo(name, description, author, stars, 0, "")
}

fun createListRepo(name: String,
	description: String,
	author: String,
	stars: Int,
	count: Int = 10): List<Repo> {
	return (1 until count).map {
		Repo(name + it, description + it, author + it, stars + it, 0, "")
	}
}