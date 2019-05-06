package `in`.co.ankitarora.templatechooser.steps.splash

import `in`.co.ankitarora.templatechooser.*
import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class SplashViewUpdater : ViewUpdater {
    private val events: PublishSubject<Event> = PublishSubject.create()

    override fun eventsObservable(): Observable<Event> {
        return events.hide().share()
    }

    @SuppressLint("CheckResult")
    override fun update(state: State, rootView: RootView) {
        state.actions.observeOn(AndroidSchedulers.mainThread()).subscribe { action ->
            when (action) {
                Action.ShowCurrentScreen -> {
                    rootView.updateView(state.currentScreen.buildView(rootView.getContext()))
                }
            }
        }
    }
}
