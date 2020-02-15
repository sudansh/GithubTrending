package com.sudansh.trending.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sudansh.trending.R
import com.sudansh.trending.util.RecyclerViewAssertions.isNotEmpty
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

	@get:Rule
	val rule = IntentsTestRule(MainActivity::class.java, true, true)

	@Before
	fun setup() {
		MockitoAnnotations.initMocks(this)
	}

	@Test
	fun loadResults() {
		onView(withId(R.id.recyclerView)).check(isNotEmpty())
		onView(withId(R.id.progressBar)).check(matches(CoreMatchers.not(isDisplayed())))
	}

	@Test
	fun testItemClickOpensDetail() {
		onView(withId(R.id.recyclerView))
			.perform(RecyclerViewActions.actionOnItemAtPosition<RepoViewHolder>(0, click()))
		intended(hasComponent(DetailActivity::class.java.name))
	}
}