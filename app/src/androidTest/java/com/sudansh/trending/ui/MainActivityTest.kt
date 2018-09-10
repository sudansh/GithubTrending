package com.sudansh.trending.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import com.sudansh.trending.R
import com.sudansh.trending.util.RecyclerViewAssertions.isNotEmpty
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = IntentsTestRule(MainActivity::class.java, true, true)
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = mock(MainViewModel::class.java)
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