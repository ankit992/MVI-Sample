package `in`.co.ankitarora.templatechooser.templateChooser

import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.Reducer
import `in`.co.ankitarora.templatechooser.Screen
import `in`.co.ankitarora.templatechooser.ViewUpdater
import android.content.Context
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class TemplateChooserScreen : Screen {
    override fun updater(): ViewUpdater = TemplateChooserViewUpdater()

    override fun reducer(): Reducer = TemplateChooserReducer()

    override fun buildView(context: Context): View {
        return TemplateChooserView(context).also {
            it.eventsObservable().subscribe { event ->
                when (event) {

                }
            }
        }
    }

    private val events = PublishSubject.create<Event>()
    override fun eventsObservable(): Observable<Event> = events.hide().share()
}
