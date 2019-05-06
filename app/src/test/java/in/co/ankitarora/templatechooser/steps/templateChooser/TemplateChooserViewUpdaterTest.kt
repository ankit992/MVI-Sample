package `in`.co.ankitarora.templatechooser.steps.templateChooser

import `in`.co.ankitarora.templatechooser.*
import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class TemplateChooserViewUpdaterTest{

    private fun viewUpdater(): TemplateChooserViewUpdater = TemplateChooserViewUpdater()

    private fun mainActivity(): MainActivity {
        return Robolectric.setupActivity(MainActivity::class.java)
    }

    private fun rootView(): RootView{
        return Mockito.mock(RootView::class.java)
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
        assertEquals(textView, activity.currentView())
    }

    @Test
    fun itShouldShowErrorScreen(){
        val mockRootView = rootView()
        val viewUpdater = viewUpdater()
        val mockCurrentView = Mockito.mock(TestView::class.java)
        Mockito.`when`(mockRootView.currentView()).thenReturn(mockCurrentView)
        val state = State(currentScreen = TemplateChooserScreen(), actions = Observable.just(Action.ShowErrorScreen))
        viewUpdater.update(state,mockRootView)
        Mockito.verify(mockRootView).hideProgressBar()
        Mockito.verify(mockCurrentView).showRetryButton()
    }

    @Test
    fun itShouldLoadTemplateData(){
        val mockRootView = rootView()
        val viewUpdater = viewUpdater()
        val mockCurrentView = Mockito.mock(TestView::class.java)
        Mockito.`when`(mockRootView.currentView()).thenReturn(mockCurrentView)
        val state = State(currentScreen = TemplateChooserScreen(), actions = Observable.just(Action.TemplateDataLoaded(
            listOf())))
        viewUpdater.update(state,mockRootView)
        Mockito.verify(mockRootView).hideProgressBar()
        Mockito.verify(mockCurrentView).showTemplates(listOf())
    }

    @Test
    fun itShouldCallBackendToLoadTemplates(){
        val mockRootView = rootView()
        val viewUpdater = viewUpdater()
        val mockCurrentView = Mockito.mock(TestView::class.java)
        val testObserver = PublishSubject.create<List<TemplateDetails>>()
        Mockito.`when`(mockRootView.getTemplatesData()).thenReturn(testObserver)
        Mockito.`when`(mockRootView.currentView()).thenReturn(mockCurrentView)
        val state = State(currentScreen = TemplateChooserScreen(), actions = Observable.just(Action.GetTemplatesData))
        viewUpdater.update(state,mockRootView)
        Mockito.verify(mockRootView).showProgressBar()
        Mockito.verify(mockRootView).getTemplatesData()

        Observable.create<Event> { emitter ->
            viewUpdater.eventsObservable().subscribe { event ->
                emitter.onNext(event)
                emitter.onComplete()
            }
            testObserver.onNext(listOf())
        }.timeout(5, TimeUnit.SECONDS).blockingFirst()
            .let {
                assertEquals(it,Event.TemplateDataLoaded(listOf()))
            }

        Observable.create<Event> { emitter ->
            viewUpdater.eventsObservable().subscribe { event ->
                emitter.onNext(event)
                emitter.onComplete()
            }
            testObserver.onError(IndexOutOfBoundsException())
        }.timeout(5, TimeUnit.SECONDS).blockingFirst()
            .let {
                assertEquals(it,Event.OnTemplateLoadError)
            }

    }
}