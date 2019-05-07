package `in`.co.ankitarora.templatechooser.steps

import `in`.co.ankitarora.templatechooser.screens.TemplateChooserTestScreen
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import cucumber.api.java.en.Then
import cucumber.runtime.java.StepDefAnnotation
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@StepDefAnnotation
class TemplateChooserSteps(private val templateChooserTestScreen: TemplateChooserTestScreen){
    @Then("I should see SlidingViewPager View")
    fun i_should_see_sliding_view_pager_view(){
        templateChooserTestScreen.validateTemplateChooserView()
    }

    @Then("The ViewPager should have 40 items in it")
    fun the_viewpager_should_have_40_items_in_it(){
        templateChooserTestScreen.validateNumberOfItemsInViewPager(40)
    }
}