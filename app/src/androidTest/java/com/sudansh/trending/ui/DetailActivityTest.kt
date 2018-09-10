package com.sudansh.trending.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.sudansh.trending.R
import com.sudansh.trending.createRepo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {
	@get:Rule
	var mActivityRule = IntentsTestRule<DetailActivity>(DetailActivity::class.java, false, false)

	@Test
	fun launchWithValidIntent() {
		val context = InstrumentationRegistry.getInstrumentation().targetContext
		val intent = Intent(context, DetailActivity::class.java)
		intent.putExtra(DetailActivity.KEY_REPO, createRepo("foo", "bar", "zol", 1))
		mActivityRule.launchActivity(intent)

		onView(ViewMatchers.withId(R.id.name)).check(ViewAssertions.matches(withText("foo")))
	}
}