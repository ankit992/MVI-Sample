package `in`.co.ankitarora.templatechooser.templateChooser

import `in`.co.ankitarora.templatechooser.MainActivity
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLooper
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class TemplateChooserScreenTest {
    private fun screen(): TemplateChooserScreen = TemplateChooserScreen()

    @Test
    fun itShouldBuildAndProvideTemplateChooserView() {
        val templateChooserView =
            screen().buildView(
                Robolectric.buildActivity(MainActivity::class.java).get()
            ) as TemplateChooserView
        assertNotNull(templateChooserView)
    }

    @Test
    fun itShouldProvideTemplateChooserReducer() {
        assertTrue(screen().reducer() is TemplateChooserReducer)
    }

    @Test
    fun itShouldProvideTemplateChooserViewUpdater() {
        assertTrue(screen().updater() is TemplateChooserViewUpdater)
    }

    @Test
    fun shouldPropogateEventsToReducer() {
        val templateChooserView =
            screen().buildView(
                Robolectric.buildActivity(MainActivity::class.java).get()
            ) as TemplateChooserView
        Observable.create<TemplateChooserViewEvents> { emitter ->
            templateChooserView.eventsObservable().subscribe {
                emitter.onNext(it)
                emitter.onComplete()
            }
            ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
        }.timeout(1, TimeUnit.SECONDS).blockingFirst().let {
            Assert.assertEquals(it, TemplateChooserViewEvents.GetTemplateData)
        }
    }
}