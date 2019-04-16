package com.sudansh.trending.util

import androidx.test.espresso.ViewAssertion
import org.junit.Assert

object RecyclerViewAssertions {

	fun isNotEmpty(): ViewAssertion {
		return ViewAssertion { view, e ->
			if (view !is androidx.recyclerview.widget.RecyclerView) throw e
			Assert.assertTrue(view.adapter?.itemCount ?: 0 > 0)
		}
	}
}