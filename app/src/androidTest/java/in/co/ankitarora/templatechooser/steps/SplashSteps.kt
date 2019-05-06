package `in`.co.ankitarora.templatechooser.steps

import `in`.co.ankitarora.templatechooser.screens.SplashTestScreen
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import cucumber.api.java.en.Given
import cucumber.runtime.java.StepDefAnnotation
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@StepDefAnnotation
class SplashSteps(private val splashTestScreen: SplashTestScreen){

    @Given("I am a user")
    fun i_am_a_user(){

    }

}