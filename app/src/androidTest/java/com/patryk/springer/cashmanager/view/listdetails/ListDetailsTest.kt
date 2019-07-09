package com.patryk.springer.cashmanager.view.listdetails

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.patryk.springer.cashmanager.R
import com.patryk.springer.cashmanager.view.main.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Patryk Springer on 2019-06-17.
 */
@RunWith(AndroidJUnit4::class)
class ListDetailsTest {


    @Test
    fun testAddNewList() {
        val newListName: String =
            (1..10).map { ('a'..'z').toList().toTypedArray().random() }.joinToString("")
        launchActivity<MainActivity>()
        onView(
            allOf(
                isDisplayed(),
                withId(R.id.fab_active_shopping_list_create)
            )
        ).perform(
            click()
        )
        onView(withId(R.id.et_create_list_name)).perform(typeText(newListName))
        onView(withId(R.id.btn_create_list_submit)).perform(click())
        onView(allOf(withId(R.id.tv_row_shopping_list_name), withText(newListName))).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun testRemoveList() {
        val newListName: String =
            (1..10).map { ('a'..'z').toList().toTypedArray().random() }.joinToString("")
        launchActivity<MainActivity>()
        onView(
            allOf(
                isDisplayed(),
                withId(R.id.fab_active_shopping_list_create)
            )
        ).perform(
            click()
        )
        onView(withId(R.id.et_create_list_name)).perform(typeText(newListName))
        onView(withId(R.id.btn_create_list_submit)).perform(click())

        onView(allOf(withId(R.id.tv_row_shopping_list_name), withText(newListName)))
            .perform(longClick())

        onView(withId(R.id.menu_delete)).perform(click())

        onView(allOf(withId(R.id.tv_row_shopping_list_name), withText(newListName)))
            .check(doesNotExist())
    }


}