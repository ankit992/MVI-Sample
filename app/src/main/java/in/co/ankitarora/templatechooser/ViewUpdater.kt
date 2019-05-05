package `in`.co.ankitarora.templatechooser

import io.reactivex.Observable

interface ViewUpdater {
    fun update(state: State, rootView: RootView)
    fun eventsObservable(): Observable<Event>
}
