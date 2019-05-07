package `in`.co.ankitarora.templatechooser.screens

import `in`.co.ankitarora.templatechooser.R
import `in`.co.ankitarora.templatechooser.steps.templateChooser.TemplatesPagerAdapter
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.viewpager.widget.ViewPager
import org.junit.Assert.assertEquals

class TemplateChooserTestScreen {
    fun validateTemplateChooserView() {
        Espresso.onView(withId(R.id.template_details_view_pager))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun validateNumberOfItemsInViewPager(numberOfItems: Int) {
        Espresso.onView(withId(R.id.template_details_view_pager)).check { view, _ ->
            assertEquals(((view as ViewPager).adapter as TemplatesPagerAdapter).count, numberOfItems)
        }
    }

}