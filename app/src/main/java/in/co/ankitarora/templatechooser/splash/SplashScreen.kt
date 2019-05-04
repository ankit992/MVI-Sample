package `in`.co.ankitarora.templatechooser.splash

import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.Reducer
import `in`.co.ankitarora.templatechooser.Screen
import `in`.co.ankitarora.templatechooser.ViewUpdater
import android.content.Context
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SplashScreen : Screen {
    private val events: PublishSubject<Event> = PublishSubject.create()

    override fun buildView(context: Context): View = SplashView(context).also {
        it.eventsObservable().subscribe { event ->
            when (event) {

            }
        }
    }


    override fun eventsObservable(): Observable<Event> = events.hide().share()

    override fun reducer(): Reducer = SplashReducer()

    override fun updater(): ViewUpdater = SplashViewUpdater()

}
