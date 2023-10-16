package com.nine.ninenews.data.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nine.ninenews.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsAppUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNewsItemClick() {

        // Delay for a few seconds to allow the RecyclerView to populate with data
        Thread.sleep(1000)

        // Click on a news item in the RecyclerView
        onView(withId(R.id.recyclerViewNews))
            .perform(actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(0, click()))

        // Verify that you've navigated to the WebView or another screen as expected
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }
}