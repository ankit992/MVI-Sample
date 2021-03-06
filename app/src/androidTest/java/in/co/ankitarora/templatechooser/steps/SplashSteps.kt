package `in`.co.ankitarora.templatechooser.steps

import `in`.co.ankitarora.templatechooser.screens.SplashTestScreen
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.runtime.java.StepDefAnnotation
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@StepDefAnnotation
class SplashSteps(private val splashTestScreen: SplashTestScreen){

    @Given("I am a user")
    fun i_am_a_user(){

    }

    @Then("I should see a 'Go To TemplateChooser' button")
    fun i_should_see_a_go_to_template_chooser_button() {
        splashTestScreen.validateTemplateChooserButtonIsVisible()
    }


}