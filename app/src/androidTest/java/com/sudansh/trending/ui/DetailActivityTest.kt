package com.sudansh.trending.ui

import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
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