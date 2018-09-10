package com.sudansh.trending.util

import android.support.test.espresso.ViewAssertion
import android.support.v7.widget.RecyclerView
import org.junit.Assert

object RecyclerViewAssertions {

    fun isNotEmpty(): ViewAssertion {
        return ViewAssertion { view, e ->
            if (view !is RecyclerView) throw e
            Assert.assertTrue(view.adapter.itemCount > 0)
        }
    }
}