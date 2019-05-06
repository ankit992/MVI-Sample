package `in`.co.ankitarora.templatechooser.steps.templateChooser

import `in`.co.ankitarora.templatechooser.*
import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class TemplateChooserViewUpdater : ViewUpdater {
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
                Action.GetTemplatesData -> {
                    rootView.showProgressBar()
                    rootView.getTemplatesData()
                        .doOnNext {
                            events.onNext(Event.TemplateDataLoaded(it))
                        }
                        .doOnError {
                            events.onNext(Event.OnTemplateLoadError)
                            it.printStackTrace()
                        }.subscribe()
                }
                Action.ShowErrorScreen -> {
                    rootView.hideProgressBar()
                    (rootView.currentView() as TemplateChooserViewInterface).showRetryButton()
                }
                is Action.TemplateDataLoaded -> {
                    rootView.hideProgressBar()
                    ((rootView.currentView()) as TemplateChooserViewInterface).showTemplates(action.templateDetails)
                }
            }
        }
    }
}