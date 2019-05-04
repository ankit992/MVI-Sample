package `in`.co.ankitarora.templatechooser

import android.content.Context
import android.view.View
import io.reactivex.Observable

interface Screen {

    fun updater(): ViewUpdater
    fun reducer(): Reducer
    fun eventsObservable(): Observable<Event>
    fun buildView(context: Context): View
}
