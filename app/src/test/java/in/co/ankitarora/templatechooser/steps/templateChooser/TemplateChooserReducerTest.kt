package `in`.co.ankitarora.templatechooser.steps.templateChooser

import `in`.co.ankitarora.templatechooser.Action
import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.State
import `in`.co.ankitarora.templatechooser.steps.splash.SplashScreen
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class TemplateChooserReducerTest {
    private fun reducer(): TemplateChooserReducer = TemplateChooserReducer()

    private fun initialState(): State {
        val screens = listOf(SplashScreen(), TemplateChooserScreen())
        return State(
            currentScreen = screens.last(),
            splashScreen = screens.first() as SplashScreen,
            templateChooserScreen = screens.last() as TemplateChooserScreen
        )
    }

    @Test
    fun itShouldShowTemplateChooserScreen() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.LoadData)
        assertEquals(nextState.templateChooserScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        assertEquals(1, actionList.blockingGet().count())
        Assert.assertTrue(actionList.blockingGet().find { action -> action == Action.ShowCurrentScreen } != null)
    }

    @Test
    fun itShouldCallGetTemplatesData() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.GetTemplateData)
        assertEquals(nextState.templateChooserScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        assertEquals(1, actionList.blockingGet().count())
        Assert.assertTrue(actionList.blockingGet().find { action -> action == Action.GetTemplatesData } != null)
    }

    @Test
    fun itShouldShowErrorOnLoadDataError() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.OnTemplateLoadError)
        assertEquals(nextState.templateChooserScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        assertEquals(1, actionList.blockingGet().count())
        Assert.assertTrue(actionList.blockingGet().find { action -> action == Action.ShowErrorScreen } != null)
    }

    @Test
    fun itShouldShowTemplatesIfTemplateDataLoaded() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.TemplateDataLoaded(listOf()))
        assertEquals(nextState.templateChooserScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        assertEquals(1, actionList.blockingGet().count())
        Assert.assertTrue(actionList.blockingGet().find { action -> action == Action.TemplateDataLoaded(listOf()) } != null)
    }

    @Test
    fun itShouldReturnZeroActionsIfUnsupportedEventSent() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.NavigateToTemplateChooserScreen)
        assertEquals(nextState.templateChooserScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        assertEquals(0, actionList.blockingGet().count())
    }
}