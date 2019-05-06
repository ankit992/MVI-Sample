package `in`.co.ankitarora.templatechooser.splash

import `in`.co.ankitarora.templatechooser.*
import android.view.View
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class SplashViewUpdaterTest{

    private fun viewUpdater():SplashViewUpdater = SplashViewUpdater()

    private fun mainActivity(): MainActivity {
        return Robolectric.setupActivity(MainActivity::class.java)
    }


    @Test
    fun showCurrentScreenShouldBuildTheViewAndAddItToActivity(){
        val mockScreen = Mockito.mock(Screen::class.java)
        val activity = mainActivity()
        val viewUpdater = viewUpdater()
        val textView = View(RuntimeEnvironment.application)

        Mockito.`when`(mockScreen.buildView(activity.getContext())).thenReturn(textView)
        val state = State(currentScreen = mockScreen, actions = Observable.just(Action.ShowCurrentScreen))

        viewUpdater.update(state, activity)

        Assert.assertEquals(textView, activity.currentView())
    }


}