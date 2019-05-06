package `in`.co.ankitarora.templatechooser.steps.templateChooser

import `in`.co.ankitarora.templatechooser.Action
import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.Reducer
import `in`.co.ankitarora.templatechooser.State
import io.reactivex.Observable

class TemplateChooserReducer : Reducer {
    override fun reduce(state: State, event: Event): State {
        return when (event) {
            is Event.LoadData -> state.copy(actions = Observable.just(Action.ShowCurrentScreen))
            is Event.GetTemplateData -> state.copy(actions = Observable.just(Action.GetTemplatesData))
            is Event.OnTemplateLoadError -> state.copy(actions = Observable.just(Action.ShowErrorScreen))
            is Event.TemplateDataLoaded -> state.copy(actions = Observable.just(Action.TemplateDataLoaded(event.templateDetails)))
            else -> state
        }
    }
}