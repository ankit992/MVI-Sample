package `in`.co.ankitarora.templatechooser.screens

import `in`.co.ankitarora.templatechooser.R
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

class TemplateChooserTestScreen {
    fun validateTemplateChooserView() {
        Espresso.onView(withId(R.id.template_details_view_pager)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}