package `in`.co.ankitarora.templatechooser.splash

import `in`.co.ankitarora.templatechooser.Action
import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.State
import `in`.co.ankitarora.templatechooser.templateChooser.TemplateChooserScreen
import org.junit.Assert
import org.junit.Test

class SplashReducerTest {
    private fun reducer(): SplashReducer = SplashReducer()

    private fun initialState(): State {
        val screens = listOf(SplashScreen(), TemplateChooserScreen())
        return State(
            currentScreen = screens.first(),
            splashScreen = screens.first() as SplashScreen,
            templateChooserScreen = screens.last() as TemplateChooserScreen
        )
    }

    @Test
    fun itShouldShowSplashScreen() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.LoadData)

        Assert.assertEquals(nextState.splashScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        Assert.assertEquals(1, actionList.blockingGet().count())
        Assert.assertTrue(actionList.blockingGet().find { action -> action == Action.ShowCurrentScreen } != null)
    }

    @Test
    fun itShouldSwitchToHomeScreenOnNavigateToTemplateChooserScreen() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.NavigateToTemplateChooserScreen)
        Assert.assertEquals(nextState.templateChooserScreen, nextState.currentScreen)
    }

    @Test
    fun itShouldReturnZeroActionsIfUnsupportedEventSent() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.OnTemplateLoadError)
        Assert.assertEquals(nextState.splashScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        Assert.assertEquals(0, actionList.blockingGet().count())
    }

}