package `in`.co.ankitarora.templatechooser.steps.splash

import `in`.co.ankitarora.templatechooser.MainActivity
import `in`.co.ankitarora.templatechooser.R
import android.widget.Button
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class SplashScreenTest {

    private fun screen(): SplashScreen = SplashScreen()

    @Test
    fun itShouldBuildAndProvideSplashView() {
        val splashView =
            screen().buildView(
                Robolectric.buildActivity(MainActivity::class.java).get()
            ) as SplashView
        Assert.assertNotNull(splashView)
    }

    @Test
    fun itShouldProvideSplashReducer() {
        assertTrue(screen().reducer() is SplashReducer)
    }

    @Test
    fun itShouldProvideSplashViewUpdater() {
        assertTrue(screen().updater() is SplashViewUpdater)
    }

    @Test
    fun shouldPropogateEventsToReducer() {
        val splashView =
            screen().buildView(
                Robolectric.buildActivity(MainActivity::class.java).get()
            ) as SplashView
        Observable.create<SplashViewEvents> { emitter ->
            splashView.eventsObservable().subscribe {
                emitter.onNext(it)
                emitter.onComplete()
            }
            splashView.findViewById<Button>(R.id.button_template_chooser).performClick()
        }.timeout(1, TimeUnit.SECONDS).blockingFirst().let {
            assertEquals(it, SplashViewEvents.NavigateToTemplateChooserScreen)
        }
    }

}