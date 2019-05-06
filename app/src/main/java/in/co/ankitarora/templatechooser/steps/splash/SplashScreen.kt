package `in`.co.ankitarora.templatechooser.steps.splash

import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.Reducer
import `in`.co.ankitarora.templatechooser.Screen
import `in`.co.ankitarora.templatechooser.ViewUpdater
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SplashScreen : Screen {
    private val events: PublishSubject<Event> = PublishSubject.create()

    override fun buildView(context: Context): View = SplashView(context).also {
        it.eventsObservable().subscribe { event ->
            when (event) {
                is SplashViewEvents.NavigateToTemplateChooserScreen -> events.onNext(Event.NavigateToTemplateChooserScreen)
            }
        }
    }


    override fun eventsObservable(): Observable<Event> = events.hide().share()

    override fun reducer(): Reducer = SplashReducer()

    @SuppressLint("CheckResult")
    override fun updater(): ViewUpdater {
        val splashViewUpdater = SplashViewUpdater()
        splashViewUpdater.eventsObservable().subscribe { event ->
            events.onNext(event)
        }
        return splashViewUpdater
    }

}
