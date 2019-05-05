package `in`.co.ankitarora.templatechooser.splash

import `in`.co.ankitarora.templatechooser.*
import android.widget.TextView
import io.reactivex.Observable
import junit.framework.Assert
import org.junit.Test
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment

class SplashViewUpdaterTest{

    private fun viewUpdater():SplashViewUpdater = SplashViewUpdater()

    private fun mainActivity(): MainActivity {
        return Robolectric.setupActivity(MainActivity::class.java)
    }


    @Test
    fun showCurrentScreenShouldBuildTheViewAndAddItToActivity(){
        val mockScreen = Mockito.mock(Screen::class.java)
        val textView = TextView(RuntimeEnvironment.application.applicationContext)
        val activity = mainActivity()
        val viewUpdater = viewUpdater()

        Mockito.`when`(mockScreen.buildView(activity)).thenReturn(textView)
        val state = State(currentScreen = mockScreen, actions = Observable.just(Action.ShowCurrentScreen))

        viewUpdater.update(state, activity)

        Assert.assertEquals(textView, activity.currentView())
    }
}