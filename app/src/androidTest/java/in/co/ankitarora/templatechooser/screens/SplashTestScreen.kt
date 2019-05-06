package `in`.co.ankitarora.templatechooser.screens

import `in`.co.ankitarora.templatechooser.R
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

class SplashTestScreen{
    fun validateTemplateChooserButtonIsVisible() {
        Espresso.onView(withId(R.id.button_template_chooser))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(click())
    }

}