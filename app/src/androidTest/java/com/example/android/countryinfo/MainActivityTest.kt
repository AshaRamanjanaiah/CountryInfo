package com.example.android.countryinfo

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.countryinfo.CustomAssertions.Companion.hasItemCount
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var rule  = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkIfDataPresentOnTheScreen() {
        onView(withId(R.id.bt_refresh))
            .perform(click())

        onView(withId(R.id.rv_canada_info))
            .inRoot(RootMatchers.withDecorView(
                Matchers.`is`(rule.getActivity().getWindow().getDecorView())))
            .check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun dataNotPresentOnTheScreen() {

        onView(withId(R.id.rv_canada_info))
            .check(hasItemCount(0))

    }

}