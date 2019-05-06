package `in`.co.ankitarora.templatechooser.steps.templateChooser

import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.Reducer
import `in`.co.ankitarora.templatechooser.Screen
import `in`.co.ankitarora.templatechooser.ViewUpdater
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class TemplateChooserScreen : Screen {
    @SuppressLint("CheckResult")
    override fun updater(): ViewUpdater {
        val templateChooserViewUpdater = TemplateChooserViewUpdater()
        templateChooserViewUpdater.eventsObservable().subscribe { event ->
            events.onNext(event)
        }
        return templateChooserViewUpdater
    }

    override fun reducer(): Reducer = TemplateChooserReducer()

    override fun buildView(context: Context): View {
        return TemplateChooserView(context).also {
            it.eventsObservable().subscribe { event ->
                when (event) {
                    TemplateChooserViewEvents.GetTemplateData -> events.onNext(Event.GetTemplateData)
                }
            }
        }
    }

    private val events = PublishSubject.create<Event>()
    override fun eventsObservable(): Observable<Event> = events.hide().share()
}
